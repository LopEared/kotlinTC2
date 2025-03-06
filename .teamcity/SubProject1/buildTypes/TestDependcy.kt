import _self.*
import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.ui.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.BuildTypeSettings.Type.DEPLOYMENT
import jetbrains.buildServer.configs.kotlin.FailureAction.FAIL_TO_START

// import SubProject1.buildTypes.TestCommands

object DependencyConfig_Test : BuildType({
    name = "DependencyConfig_Test"
    description = "Dependency Config Test"

    type = DEPLOYMENT
    enablePersonalBuilds = false
    maxRunningBuilds = 1

    // dependencies {
    //     dependency(SubProject_Nest3_buildConfig) {
    //         snapshot {
    //             onDependencyFailure = FAIL_TO_START
    //         }
    
    //         artifacts {
    //             enabled = false
    //         }
    //     }
    // }

    params {
        text("FirstParam", "Value_First_Param", label = "1_Param: Iput value! ->")
        text("SecondParam", "Value_Second_Param", label = "2_Param: Iput value! ->")
        text("confirmVcsBranchName", "", label = "Confirm branch name:", display = ParameterDisplay.PROMPT, allowEmpty = false)
        param("reverse.dep.${SubProject1_TestCommands.id}.confirmVcsBranchName", %confirmVcsBranchName%)
    }

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        DependencyTest_FunctionStep()
        script {
                name = "TestPassVarIntoDependecy"
                workingDir = "/"
                scriptContent = """echo "%confirmVcsBranchName%" """
            }
    }

    requirements {
        equals("teamcity.agent.name", "ip_172.17.0.1")
    }
})

private fun BuildSteps.DependencyTest_FunctionStep() {
    script {
        name = "Dependency Test Function"
        id = "TestDependcy_BuildConfiguration"
        workingDir = "/"
        scriptContent = """
                #!/bin/bash

                firstVal=%FirstParam%
                secondVal="%SecondParam%"

                echo "VCS branch is: '%teamcity.build.branch%'"
                echo "##teamcity[message text='<<< TEST ERROR MESSAGE!!! >>>' status='WARNING']"

                echo "Nontes For Dependcy Build Configuiration: " >  %build.number%_%teamcity.build.branch%_testFile.txt

                cat >multi_line_%build.number%_%teamcity.build.branch%_testFile.txt <<EOL
                Env Wrote from TeamCity First PARAM : %FirstParam%
                Env Wrote from TeamCity SECOND PARAM : %SecondParam%
                -----------------------
                Env Wrote from BASH  First PARAM : $!firstVal
                Env Wrote from BASH  First PARAM : $!secondVal
                EOL

                sleep 10
                """.trimIndent().replace("$!", "$")
    }
}