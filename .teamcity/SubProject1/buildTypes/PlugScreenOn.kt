import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.ui.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.BuildTypeSettings.Type.DEPLOYMENT

object PlugScreenOn : BuildType({
    name = "PlugScreenOn_NAME"
    description = "TURN ON PLUG SCREEN"

    type = DEPLOYMENT
    enablePersonalBuilds = false
    maxRunningBuilds = 1

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            name = "Plug Turn ON"
            id = "PlugTurnON_STEP"
            workingDir = "/"
            scriptContent = """
                echo "Test Moving PlugPage.html"
                cd / && mv maintenance-stage.html2 maintenance-stage.html || exit 1
            """.trimIndent()
        }
    }

    requirements {
        equals("teamcity.agent.name", "ip_172.17.0.1")
    }
})