<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="height=device-height,
                      width=device-width, initial-scale=1.0,
                      minimum-scale=1.0, maximum-scale=1.0,
                      user-scalable=yes">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/homepage.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/all.css" type="text/css">

    <title>Homepage</title>
</head>
<body class="main-body">
<header class="navbar">
    <a href="Homepage"><img class="logo-efrei" src="${pageContext.request.contextPath}/Image/Logo-Efrei-2017-Fr-Web.png"
                            alt="efrei"></a>
    <div class="navbar-menu">
        <jsp:useBean id="tutor" class="models.TutorEntity" scope="session"/>
        <p>
            ${tutor.firstname}${" "}${tutor.name}
        </p>
        <a href="Logout">Logout</a>
    </div>
</header>
<main class="homepage-main">
    <div class="homepage-main-content">
        <h1>Extranet myEfrei - ${fn:length(listOfInternship)}
            <c:choose>
                <c:when test="${fn:length(listOfInternship) > 1}">
                    internships selected
                    <br/>
                </c:when>
                <c:otherwise>
                    internship selected
                    <br/>
                </c:otherwise>
            </c:choose>
        </h1>
        <form class="homepage-selector">
            <div class="homepage-selector-select">
                <label for="year">Year</label>
                <select name="year" id="year">
                    <option value="%">All</option>
                    <c:forEach var="year" begin="0" end="7" step="1">
                    <option value="${2022 - year}"
                            <c:choose>
                                <c:when test="${searchedYear == 2022 - year}">
                                        selected="selected"
                                </c:when>
                            </c:choose>

                        >${2022 - year}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="homepage-selector-select">
                <label for="keywords">Keywords</label>
                <select name="keywords" id="keywords">
                    <option value="-">-</option>
                    <c:forEach items="${listOfKeywords}" var="keyword">
                        <option value="${keyword.keyword}"
                            <c:choose>
                                <c:when test="${searchedKeyword == keyword.keyword}">
                                    selected="selected"
                                </c:when>
                            </c:choose>
                        >${keyword.keyword}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="homepage-selector-select">
                <label for="search-name">Name</label>
                <input type="text" name="search-name" id="search-name"
                    <c:choose>
                        <c:when test="${not empty searchedName}">
                               value="${searchedName}"
                        </c:when>
                    </c:choose>
                >
            </div>
            <input type="submit" value="Search">
        </form>

        <c:choose>
            <c:when test="${empty listOfInternship}">
                <div>
                    <h2>No data ! </h2>
                    <p>It seems that no internship have been found with your criteria</p>
                </div>
            </c:when>
            <c:otherwise>
                <div class="homepage-table-wrapper">
                    <div class="homepage-table">
                        <div class="homepage-row">
                            <span class="homepage-cell"></span>
                            <span class="homepage-cell">#</span>
                            <span class="homepage-cell">Group</span>
                            <span class="homepage-cell">First name</span>
                            <span class="homepage-cell">Last name</span>
                            <span class="homepage-cell">C.d.C</span>
                            <span class="homepage-cell">Company eval</span>
                            <span class="homepage-cell">Web survey</span>
                            <span class="homepage-cell">Released report</span>
                            <span class="homepage-cell">Defense</span>
                            <span class="homepage-cell">Planned visit</span>
                            <span class="homepage-cell">Visit done</span>
                            <span class="homepage-cell">Begin date</span>
                            <span class="homepage-cell">End date</span>
                            <span class="homepage-cell">M.d.S</span>
                            <span class="homepage-cell">Tech note</span>
                            <span class="homepage-cell">Comm note</span>
                        </div>
                        <c:forEach items="${listOfInternship}" var="Internship">
                            <form class="homepage-row" name="form" action="Details" method="post">
                                    <span class="homepage-cell">
                                        <div>
                                            <input type="submit" value="details" name="internshipSubmit"/>
                                            <input type="submit" value="modify" name="internshipSubmit"/>
                                        </div>
                                    </span>
                                <input type="hidden" value="${Internship.marks.marksId}" name="marksId">
                                <input type="hidden" value="${Internship.student.studentId}" name="studentId">
                                <input type="hidden" value="${Internship.visit.visitId}" name="visitId">
                                <input type="hidden" value="${Internship.finalReport.finalReportId}" name="finalReportId">
                                <span class="homepage-cell"><input name="internshipId" type="text"
                                                                   value="${Internship.internshipId}"
                                                                   readonly/></span>
                                <span class="homepage-cell"><input name="studentGroup" type="text"
                                                                   value="${Internship.student.studentGroup}"/></span>
                                <span class="homepage-cell"><input name="studentFirstname" type="text"
                                                                   value="${Internship.student.firstname}"/></span>
                                <span class="homepage-cell"><input name="studentName" type="text"
                                                                   value="${Internship.student.name}"/></span>
                                <span class="homepage-cell"><input name="cdc"
                                                                   type="checkbox" ${Internship.cdc == true ? 'checked' : ''} /></span>
                                <span class="homepage-cell"><input name="companyEval"
                                                                   type="checkbox" ${Internship.companyEval == true ? 'checked' : ''}/></span>
                                <span class="homepage-cell"><input name="webSurvey"
                                                                   type="checkbox" ${Internship.webSurvey == true ? 'checked' : ''}/></span>
                                <span class="homepage-cell"><input name="releasedReport"
                                                                   type="checkbox" ${Internship.finalReport.report == true ? 'checked' : ''}/></span>
                                <span class="homepage-cell"><input name="defense"
                                                                   type="checkbox" ${Internship.defense == true ? 'checked' : ''} /></span>
                                <span class="homepage-cell"><input name="visitPlanned"
                                                                   type="checkbox" ${Internship.visit.planned == true ? 'checked' : ''}/></span>
                                <span class="homepage-cell"><input name="visitDone"
                                                                   type="checkbox" ${Internship.visit.done == true ? 'checked' : ''}/></span>
                                <span class="homepage-cell"><input name="beginningDate" type="date"
                                                                   value="${Internship.beginning}"/></span>
                                <span class="homepage-cell"><input name="endDate" type="date"
                                                                   value="${Internship.ending}"/></span>
                                <span class="homepage-cell"><input name="supervisor" type="text"
                                                                   value="${Internship.internSupervisor}"/></span>
                                <span class="homepage-cell"><input name="techMark" type="text"
                                                                   value="${Internship.marks.tech}"/></span>
                                <span class="homepage-cell"><input name="commMark" type="text"
                                                                   value="${Internship.marks.communication}"/></span>
                            </form>
                        </c:forEach>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>


    </div>

</main>
</body>
</html>