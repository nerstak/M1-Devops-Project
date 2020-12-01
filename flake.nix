{
    description = "M1-JEE-Project";
    inputs = {
        unstable.url = "github:NixOS/nixpkgs/nixos-unstable";
    };
    outputs = inputs:
        let 
            system = "x86_64-linux";
            pkgs = inputs.unstable.legacyPackages.${system};
        in {
            devShell."${system}" = pkgs.mkShell {
                buildInputs = with pkgs; [
                    openjdk8
                ];
            };
        };
}