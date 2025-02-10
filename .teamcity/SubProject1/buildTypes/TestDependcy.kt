import _self.*
import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.ui.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.BuildTypeSettings.Type.DEPLOYMENT

object DependencyConfig_Test : BuildType({
    name = "DependencyConfig_Test"
    description = "Dependency Config Test"

    type = DEPLOYMENT
    enablePersonalBuilds = false
    maxRunningBuilds = 1

    params {
        text("FirstParam", "Value_First_Param", label = "1_Param: Iput value! ->")
        text("SecondParam", "Value_Second_Param", label = "2_Param: Iput value! ->")
    }

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        DependencyTest_FunctionStep()
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

                echo "Nontes For Dependcy Build Configuiration: " >  %build.number%_%teamcity.build.branch%_testFile.txt

                cat >multi_line_%build.number%_%teamcity.build.branch%_testFile.txt <<EOL
                Env Wrote from TeamCity First PARAM : %FirstParam%
                Env Wrote from TeamCity SECOND PARAM : %SecondParam%
                -----------------------
                Env Wrote from BASH  First PARAM : $!firstVal
                Env Wrote from BASH  First PARAM : $!secondVal

                """.trimIndent().replace("$!", "$")
    }
}