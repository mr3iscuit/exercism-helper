{ pkgs ? import <nixpkgs> {} } :
pkgs.stdenv.mkDerivation {
    pname = "exercism-helper";
    version = "v1.0";

    buildInputs = with pkgs; [
        jre17_minimal
        exercism
    ];

    nativeBuildInputs = with pkgs; [
        makeWrapper
    ];

    src = ./build/libs;

    installPhase = ''
        mkdir -p $out/lib
        cp ./exide-helper-1.0-SNAPSHOT.jar $out/lib/exercism-helper.jar

        makeWrapper ${pkgs.jre17_minimal}/bin/java $out/bin/exercism \
          --add-flags "-cp $out/lib/exercism-helper.jar" \
          --add-flags "org.exidehelper.Main"
    '';
}