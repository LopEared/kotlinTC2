package _self

import jetbrains.buildServer.configs.kotlin.BuildSteps
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import java.io.File

fun BuildSteps.initAppVersion(versionPostfix: String = "") {
    script {
        name = "Init app version"
        scriptContent = """
                #!/bin/bash

                branchName=%teamcity.build.branch%
                commitHash=%system.build.vcs.number%
                buildNumber=%build.number%

                version="$!{branchName##*/}-$!{commitHash::11}-$!buildNumber${versionPostfix}"

                echo "##teamcity[setParameter name='appVersion' value='$!{version}']"
            """.trimIndent()
            .replace("$!", "$")
    }
}

fun BuildSteps.checkPassword() {
    script {
        name = "Check password"
        scriptContent = """
                #!/bin/bash

                enteredPassword=%password%
                expectedPassword="%deploy_pass%"

                [ -z "$!enteredPassword" ] && echo "##teamcity[message text='Password is empty' status='ERROR']" && exit 13
                [ $!enteredPassword != $!expectedPassword ] && echo "##teamcity[message text='Incorrect password' status='ERROR']" && exit 13
                exit 0
            """.trimIndent().replace("$!", "$")
    }
}

fun BuildSteps.confirmVcsBranch() {
    script {
        name = "Сheck Selected Branch"
        id = "Confirm VCS branch version"
        scriptContent = """
                #!/bin/bash
                
                echo "VCS branch is: '%teamcity.build.branch%'"
                echo "Entered branch is: '%confirmVcsBranchName%'"
                
                if [[ "%teamcity.build.branch%" == "%confirmVcsBranchName%" ]]; then
                	echo "Branches are equals -> OK!"
                else
                    echo "Entered branch is not equals VCS. Break build!"
                    exit 1
                fi
                """.trimIndent()
    }
}

// fun BuildSteps.duplicateParam(initialParamName: String, vararg otherParamNames: String) {
//     script {
//         name = "Duplicate param $initialParamName"
//         scriptContent = """
//                 #!/bin/bash
                
//                 initialParamValue=%${initialParamName}%

//                 ${otherParamNames.joinToString("\n") { "echo \"##teamcity[setParameter name='$it' value='$!{initialParamValue}']\"" }}
//             """.trimIndent()
//             .replace("$!", "$")
//     }
// }

fun getTextFromFile(pathname: String): String = File(pathname).readText().trimIndent()
