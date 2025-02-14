import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.ui.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.BuildTypeSettings.Type.REGULAR

object SubProject_Nest3_buildConfig : BuildType({
    id("SubProject1_SubProjectNest3_SubProjectNest3BuildConfig")
    name = "SubProjectNest3_BuildConfig"
    description = "BuildConfig_SubProjectNest3"

    type = REGULAR
    enablePersonalBuilds = false
    maxRunningBuilds = 1

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        SubDependcyFunction()
        // PlugScreenUP()
    }

    requirements {
        equals("teamcity.agent.name", "ip_172.17.0.1")
    }
})


private fun BuildSteps.SubDependcyFunction() {
    script {
        name = "SubDependcyFunction_NAME"
        id = "SubDependcyFunction_ID"
        workingDir = "/"
        scriptContent = """
                #!/bin/bash
                sleep 10
                echo "##teamcity[message text='<<< TEST WARNING MESSAGE!!! >>>' status='WARNING']"
                echo "Notes From SubProject Dependcy Function: %system.teamcity.projectName% from build: %build.number% " >  %build.number%_%teamcity.build.branch%_subproj_dependcy.txt
                """.trimIndent()
    }
}
