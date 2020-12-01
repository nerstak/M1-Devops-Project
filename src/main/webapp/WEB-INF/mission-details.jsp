<%--
  Created by IntelliJ IDEA.
  User: Vincent
  Date: 07/11/2020
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="height=device-height,
                      width=device-width, initial-scale=1.0,
                      minimum-scale=1.0, maximum-scale=1.0,
                      user-scalable=yes">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/mission-details.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/all.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/autocomplete.css" type="text/css">

    <title>Mission details</title>
    <script src="${pageContext.request.contextPath}/JS/autocompleteFunctions.js" ></script>
</head>
<body>
    <header class="navbar">
        <a href="Homepage"><img class="logo-efrei" src="${pageContext.request.contextPath}/Image/Logo-Efrei-2017-Fr-Web.png" alt="efrei"></a>
        <div class="navbar-menu">
            <jsp:useBean id="tutor" class="models.TutorEntity" scope="session"/>
            <p>
                ${tutor.firstname}${" "}${tutor.name}
            </p>
            <a href="Logout">Logout</a>
        </div>
    </header>


    <c:if test="${not empty message}">
        <c:choose>
            <c:when test="${message == 'The database has been updated'}">
                <div class="details_message_success" >
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 477.867 477.867"><path d="M238.933 0C106.974 0 0 106.974 0 238.933s106.974 238.933 238.933 238.933 238.933-106.974 238.933-238.933C477.726 107.033 370.834.141 238.933 0zm0 443.733c-113.108 0-204.8-91.692-204.8-204.8s91.692-204.8 204.8-204.8 204.8 91.692 204.8 204.8c-.122 113.058-91.742 204.678-204.8 204.8z"/><path d="M370.046 141.534c-6.614-6.388-17.099-6.388-23.712 0l-158.601 158.6-56.201-56.201c-6.548-6.78-17.353-6.967-24.132-.419-6.78 6.548-6.967 17.353-.419 24.132.137.142.277.282.419.419l68.267 68.267c6.664 6.663 17.468 6.663 24.132 0l170.667-170.667c6.548-6.779 6.36-17.583-.42-24.131z"/></svg>
                    <h1>${message}</h1>
                </div>
            </c:when>
            <c:otherwise>
                <div class="details_message_error">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><path d="M256 0C114.508 0 0 114.497 0 256c0 141.493 114.497 256 256 256 141.492 0 256-114.497 256-256C512 114.507 397.503 0 256 0zm0 472c-119.384 0-216-96.607-216-216 0-119.385 96.607-216 216-216 119.384 0 216 96.607 216 216 0 119.385-96.607 216-216 216z"/><path d="M343.586 315.302L284.284 256l59.302-59.302c7.81-7.81 7.811-20.473.001-28.284-7.812-7.811-20.475-7.81-28.284 0L256 227.716l-59.303-59.302c-7.809-7.811-20.474-7.811-28.284 0-7.81 7.811-7.81 20.474.001 28.284L227.716 256l-59.302 59.302c-7.811 7.811-7.812 20.474-.001 28.284 7.813 7.812 20.476 7.809 28.284 0L256 284.284l59.303 59.302c7.808 7.81 20.473 7.811 28.284 0s7.81-20.474-.001-28.284z"/></svg>
                    <h1>${message}</h1>
                </div>
            </c:otherwise>
        </c:choose>
    </c:if>

    <main class="main-info">

        <jsp:useBean id="internshipData" class="models.InternshipEntity" scope="request"/>
        <div class="wrapper-flex">
            <div class="info-company">
                <h2>Company</h2>
                <form class="info-company-form" action="UpdateDetails" method="post">
                    <div class="div-input-half">
                        <div class="input-left">
                            <label for="companyName">Name</label>
                            <input type="text" id="companyName" name="companyName" value="${internshipData.company.name}">
                        </div>
                        <div class="input-right">
                            <label for="mds">M.d.S</label>
                            <input type="text" name="mds" id="mds" value="${internshipData.internSupervisor}">
                        </div>
                    </div>
                    <div class="div-input-half">
                        <div class="input-left">
                            <label for="begin">Begin</label>
                            <input type="date" id="begin" name="begin" value="${internshipData.beginning}">
                        </div>
                        <div class="input-right">
                            <label for="end">End</label>
                            <input type="date" id="end" name="end" value="${internshipData.ending}">
                        </div>
                    </div>
                    <div class="div-input-full">
                        <label for="companyAddress">Address</label>
                        <input type="text" id="companyAddress" name="companyAddress" value="${internshipData.company.address}" >
                    </div>
                    <input type="hidden" name="internshipId" value="${internshipData.internshipId}">
                    <input type="hidden" name="companyId" value="${internshipData.company.companyId}">
                    <button type="submit" value="company" name="updateDetails">Modify</button>
                </form>
            </div>

            <div class="info-students">
                <h2>Student</h2>
                <form class="info-company-form" action="UpdateDetails" method="post" name="updateDetails">
                    <div class="div-input-half">
                        <div class="input-left">
                            <label for="studentId">ID</label>
                            <input type="text" id="studentId" name="studentId" value="${internshipData.student.studentId}" readonly>
                        </div>
                        <div class="input-right">
                            <label for="group">Group</label>
                            <input type="text" name="group" id="group" value="${internshipData.student.studentGroup}">
                        </div>
                    </div>
                    <div class="div-input-half">
                        <div class="input-left">
                            <label for="firstName">First Name</label>
                            <input type="text" id="firstName" name="firstName" value="${internshipData.student.firstname}">
                        </div>
                        <div class="input-right">
                            <label for="lastName">Last Name</label>
                            <input type="text" id="lastName" name="lastName" value="${internshipData.student.name}">
                        </div>
                    </div>
                    <div class="div-input-full">
                        <label for="email">Email</label>
                        <input type="text" id="email" name="email" value="${internshipData.student.email}">
                    </div>
                    <div class="div-input-full">
                        <label for="linkedin">Linkedin Profile</label>
                        <input type="text" id="linkedin" name="linkedin" value="${internshipData.student.linkedinProfile}">
                    </div>
                    <input type="hidden" name="internshipId" value="${internshipData.internshipId}">
                    <button type="submit" value="student" name="updateDetails">Modify</button>
                </form>
            </div>
        </div>

        <div class="wrapper-flex">
            <div class="info-internship">
                <h2>Internship</h2>
                <form class="info-company-form" id="info-company-form" action="UpdateDetails" method="post" name="updateDetails">
                    <div class="div-input-half">
                        <div class="input-left">
                            <label for="reportTitle">Report's title</label>
                            <input type="text" name="reportTitle" id="reportTitle" value="${internshipData.finalReport.title}">
                        </div>
                    </div>
                    <div class="div-input-third">
                        <div class="input-left">
                            <label for="description">Description</label>
                            <textarea name="description" id="description" form="info-company-form">${internshipData.description}</textarea>
                        </div>
                        <div class="input-right">
                            <label for="tutorComments">Tutor's comment</label>
                            <textarea name="tutorComments" id="tutorComments" form="info-company-form">${internshipData.comments.supervisorComm}</textarea>
                        </div>
                        <div class="input-right">
                            <label for="studentComments">Student's comments</label>
                            <textarea name="studentComments" id="studentComments" form="info-company-form">${internshipData.comments.studentComm}</textarea>
                        </div>
                    </div>
                    <input type="hidden" name="internshipId" value="${internshipData.internshipId}">
                    <input type="hidden" name="titleId" value="${internshipData.finalReport.finalReportId}">
                    <input type="hidden" name="commentsId" value="${internshipData.comments.commentsId}">

                    <button type="submit" value="internship" name="updateDetails">Modify</button>
                </form>
            </div>
        </div>
        <div class="wrapper-flex">
            <div class="info-skills">
                <h2>Skills</h2>
                <form class="info-company-form" action="UpdateDetails" method="post" name="updateDetails" autocomplete="off">
                    <div class="div-input-button">
                        <div class="input-left">
                            <div class="autocomplete">
                                <label for="skillInput">Skills</label>
                                <input type="text" id="skillInput" name="skill">
                            </div>
                            <input type="hidden" name="studentId" value="${internshipData.student.studentId}">
                            <input type="hidden" name="internshipId" value="${internshipData.internshipId}">
                            <button type="submit" value="skills" name="updateDetails">Add</button>
                        </div>
                    </div>
                </form>
                <div class="list-data">
                    <c:choose>
                        <c:when test="${empty internshipData.student.skills}">
                            <h2>No skills registered</h2>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${internshipData.student.skills}" var="skill">
                                <p>${skill.skill}</p>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <div class="info-keywords">
                <h2>Keyword</h2>
                <form class="info-company-form" action="UpdateDetails" method="post" name="updateDetails" autocomplete="off">
                    <div class="div-input-button">
                        <div class="input-left">
                            <div class="autocomplete">
                                <label for="keywordInput">Keywords</label>
                                <input type="text" id="keywordInput" name="keyword">
                            </div>
                            <input type="hidden" name="internshipId" value="${internshipData.internshipId}">
                            <button type="submit" value="keywords" name="updateDetails">Add</button>
                        </div>
                    </div>
                </form>
                <div class="list-data">
                    <c:choose>
                        <c:when test="${empty internshipData.listKeywords}">
                            <h2>No skills registered</h2>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${internshipData.listKeywords}" var="keywords">
                                <p>${keywords.keyword}</p>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </main>
<script>
    var skills = new Array();
    <c:forEach items="${listOfSkills}" var="skill">
        skills.push("${skill.skill}");
    </c:forEach>

    var keywords = new Array();
    <c:forEach items="${listOfKeywords}" var="keyword">
        keywords.push("${keyword.keyword}");
    </c:forEach>

    autocomplete(document.getElementById("skillInput"),skills);
    autocomplete(document.getElementById("keywordInput"),keywords);
</script>
</body>
</html>
