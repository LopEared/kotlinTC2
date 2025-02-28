package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.SSHUpload
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.buildSteps.sshUpload
import jetbrains.buildServer.configs.kotlin.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'SubProject1_TestCommands'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("SubProject1_TestCommands")) {
    params {
        expect {
            password("password", "", label = "Password", description = "Input password to start build", display = ParameterDisplay.PROMPT)
        }
        update {
            password("password", "credentialsJSON:88b29559-f978-4cc6-a1ab-34d1a44d9959", label = "Password", description = "Input password to start build", display = ParameterDisplay.PROMPT)
        }
    }

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
        script {
            name = "Create psql backup"
            scriptContent = """
                #!/bin/bash
                if [[ "%pgsqlMakeBackup%" == "true" ]]; then
                    find /testDir -type f -mmin +5 -name '*' -execdir rm -- '{}' \;
                else
                    echo "SKIPPED: Make Backup for Postgres DB consult and appoinment."
                fi
            """.trimIndent()
        }
        script {
            name = "SomeCommands"
            id = "SomeCommands_LastPOINT"
            workingDir = "/"
            scriptContent = """echo "New Nontes into files" >  %build.number%_%teamcity.build.branch%_testFile.txt"""
        }
    }
    steps {
        insert(4) {
            sshUpload {
                id = "ssh_deploy_runner"
                transportProtocol = SSHUpload.TransportProtocol.SCP
                sourcePath = "."
                targetUrl = "172.17.0.2:/tmp"
                authMethod = password {
                    username = "testCI"
                    password = "credentialsJSON:02738a5c-d6a8-4e76-8d64-2f04ac307529"
                }
            }
        }
    }

    dependencies {
        expect(RelativeId("DependencyConfig_Test")) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                artifactRules = ""
                enabled = false
            }
        }
        update(RelativeId("DependencyConfig_Test")) {
            snapshot {
                onDependencyCancel = FailureAction.CANCEL
            }

            artifacts {
                artifactRules = ""
                enabled = false
            }
        }

    }
}
