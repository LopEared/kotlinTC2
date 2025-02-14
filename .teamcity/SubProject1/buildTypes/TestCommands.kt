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
        text("confirmVcsBranchName", "", label = "Confirm branch name:", display = ParameterDisplay.PROMPT, allowEmpty = false)
        checkbox("TestCheckBox", "true", label = "Screen plug during process", description = "Will put up a screen plug before deployment and removed it after deployment.", display = ParameterDisplay.PROMPT, checked = "true", unchecked = "false")
    }

    vcs {
        root(DslContext.settingsRoot)
    }

    dependencies {
        // dependency(DependencyConfig_Test) {
        //     snapshot {
        //         onDependencyFailure = FAIL_TO_START
        //     }
        //     artifacts {
        //         enabled = false
        //     }
        // }
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
        confirmVcsBranch()
        // createFile()
        // testPassCheckBoxinBash()
        PlugScreenUP()

        script {
            name = "SomeCommands"
            id = "SomeCommands_LastPOINT"
            workingDir = "/"
            scriptContent = """echo "New Nontes into files" >  %build.number%_%teamcity.build.branch%_testFile.txt"""
        }
        
        update<ScriptBuildStep>(2) {
            clearConditions()

            conditions {
                equals("TestCheckBox", "true")
            }
        }
    }

    requirements {
        equals("teamcity.agent.name", "ip_172.17.0.1")
    }
})