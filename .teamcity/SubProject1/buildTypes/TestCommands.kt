import _self.*
import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.ui.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.buildSteps.ScriptBuildStep
import jetbrains.buildServer.configs.kotlin.BuildTypeSettings.Type.DEPLOYMENT

import jetbrains.buildServer.configs.kotlin.FailureAction.FAIL_TO_START


object SubProject1_TestCommands : BuildType({
    name = "TestCommands"
    description = "TestCommands"

    type = DEPLOYMENT
    enablePersonalBuilds = false
    maxRunningBuilds = 1

    params {
        password("deploy_pass", "credentialsJSON:3d93ad41-aacd-4523-8208-9f4cb2ca9531", display = ParameterDisplay.HIDDEN, readOnly = true)
        password("password", "", label = "Password", description = "Input password to start build", display = ParameterDisplay.PROMPT)
        text("reverse.dep.${DependencyConfig_Test.id}.confirmVcsBranchName", "", label = "Confirm branch name:", display = ParameterDisplay.PROMPT, allowEmpty = false)
        
        checkbox("TestCheckBox", "true", label = "Screen plug during process", description = "Will put up a screen plug before deployment and removed it after deployment.", display = ParameterDisplay.PROMPT, checked = "true", unchecked = "false")
        checkbox("pgsqlMakeBackup", "true", label = "Backup Postgres DB", description = "Make Backup for Postgres DB:consult and appoinment.", display = ParameterDisplay.PROMPT, checked = "true", unchecked = "false")
        // val envFile = "TEST NOTE!"
        param("env.envFile", getTextFromFile("SubProject1/recources/testFile.txt"))
    
        // text("confirmVcsBranchName", "", label = "Confirm branch name:", display = ParameterDisplay.PROMPT, allowEmpty = false)
        // param("reverse.dep.${DependencyConfig_Test.id}.confirmVcsBranchName", "", label = "Confirm branch name:", display = ParameterDisplay.PROMPT, allowEmpty = false )
        // param("reverse.dep.${DependencyConfig_Test.id}.confirmVcsBranchName", "TEST_IS_MY" )
    }

    vcs {
        root(DslContext.settingsRoot)
    }

    dependencies {
        dependency(DependencyConfig_Test) {
            snapshot {
                onDependencyFailure = FAIL_TO_START
            }
            artifacts {
                enabled = false
            }
        }
        // dependency(PlugScreenOn) {
        //     snapshot {
        //         onDependencyFailure = FAIL_TO_START
        //     }
        //     artifacts {
        //         enabled = false
        //     }
        // }
        // dependency(MiddleInChain_Test) {
        //     snapshot {
        //         onDependencyFailure = FAIL_TO_START
        //     }
        //     artifacts {
        //         enabled = false
        //     }
        // }
        // dependency(PlugScreenOff) {
        //     snapshot {
        //         onDependencyFailure = FAIL_TO_START
        //     }
        //     artifacts {
        //         enabled = false
        //     }
        // }
    }

    steps {
        checkPassword()
        // confirmVcsBranch()
        confirmVcsBranchL2("%reverse.dep.${DependencyConfig_Test.id}.confirmVcsBranchName%")
        // createFile()
        // testPassCheckBoxinBash()
        // PlugScreenUP()
        // pgsqlBackup()
        timeDelaySeconds("5")
        // script {
        //     name = "SomeCommands"
        //     id = "SomeCommands_LastPOINT"
        //     workingDir = "/"
        //     scriptContent = """echo "New Nontes into files" >  %build.number%_%teamcity.build.branch%_testFile.txt"""
        // }
        
        // update<ScriptBuildStep>(2) {
        //     clearConditions()

        //     conditions {
        //         equals("TestCheckBox", "true")
        //     }
        // }

        // sshUpload {
        //     name = "TestDeployment"
        //     id = "TestDeployment"
        //     transportProtocol = SSHUpload.TransportProtocol.SCP
        //     sourcePath = "/"
        //     targetUrl = "172.17.0.2:/tmp"
        //     authMethod = password {
        //         username = "testCI"
        //         password = "credentialsJSON:02738a5c-d6a8-4e76-8d64-2f04ac307529"
        //     }
        // }
    }

    requirements {
        equals("teamcity.agent.name", "ip_172.17.0.1")
    }
})



fun BuildSteps.confirmVcsBranchL2(inputedBranch: String) {
    script {
        name = "Сheck Selected Branch"
        id = "Confirm VCS branch version"
        workingDir = "/"
        scriptContent = """
                #!/bin/bash
                echo "%env.envFile%" > %build.number%_testENV_FILE.txt
                echo -e "\n\n\n\n THIS IS MY FUNCTION!!!!"
                echo "VCS branch is: '%teamcity.build.branch%'"
                echo "Entered branch is: '$inputedBranch'"
                
                if [[ "%teamcity.build.branch%" == "$inputedBranch" ]]; then
                	echo "Branches are equals -> OK!"
                else
                    echo "Entered branch is not equals VCS. Break build!"
                    exit 1
                fi
                """.trimIndent()
    }
}