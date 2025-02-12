import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.ui.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.BuildTypeSettings.Type.DEPLOYMENT

object PlugScreenOff : BuildType({
    name = "PlugScreenOff_NAME"
    description = "TURN OFF PLUG SCREEN"

    type = DEPLOYMENT
    enablePersonalBuilds = false
    maxRunningBuilds = 1

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        PlugScreenDOWN()
    }

    requirements {
        equals("teamcity.agent.name", "ip_172.17.0.1")
    }
})