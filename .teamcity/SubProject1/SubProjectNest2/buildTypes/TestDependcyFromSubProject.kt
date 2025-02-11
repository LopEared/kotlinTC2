import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.ui.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.BuildTypeSettings.Type.REGULAR

object SubProject_Nest2_buildConfig : BuildType({
    name = "SubProject_Nest2_BuildType"
    description = "BuildConfig for  Test dependcy from subproject"

    type = REGULAR
    enablePersonalBuilds = false
    maxRunningBuilds = 1

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        SubDependcyFunction()
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
                echo "##teamcity[message text='<<< TEST WARNING MESSAGE!!! >>>' status='WARNING']"
                echo "Notes From SubProject Dependcy Function: " >  %build.number%_%teamcity.build.branch%_subproj_dependcy.txt
                """.trimIndent()
    }
}
