package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.ScriptBuildStep
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'SubProject1_TestCommands'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("SubProject1_TestCommands")) {
    expectSteps {
        script {
            name = "Check password"
            scriptContent = """
                #!/bin/bash
                
                enteredPassword=%password%
                expectedPassword="%deploy_pass%"
                
                [ -z "${'$'}enteredPassword" ] && echo "##teamcity[message text='Password is empty' status='ERROR']" && exit 13
                [ ${'$'}enteredPassword != ${'$'}expectedPassword ] && echo "##teamcity[message text='Incorrect password' status='ERROR']" && exit 13
                exit 0
            """.trimIndent()
        }
        script {
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
        script {
            name = "Create Test Condition File"
            workingDir = "/"
            scriptContent = """
                echo "StartCreate File"
                touch %build.number%_conditionFile.txt
                sleep 5
            """.trimIndent()
        }
        script {
            name = "SomeCommands"
            id = "SomeCommands"
            workingDir = "/"
            scriptContent = """echo "New Nontes into files" >  %build.number%_%teamcity.build.branch%_testFile.txt"""
        }
    }
    steps {
        update<ScriptBuildStep>(2) {
            clearConditions()

            conditions {
                equals("TestCheckBox", "true")
            }
        }
    }
}
