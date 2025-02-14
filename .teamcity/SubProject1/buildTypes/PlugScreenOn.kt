import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.ui.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.BuildTypeSettings.Type.DEPLOYMENT
// import SubProject1.buildTypes

object PlugScreenOn : BuildType({
    name = "PlugScreenOn_NAME"
    description = "TURN ON PLUG SCREEN"

    type = DEPLOYMENT
    enablePersonalBuilds = false
    maxRunningBuilds = 1

    vcs {
        root(DslContext.settingsRoot)
    }

    params {
        checkbox("TestCheckBox", "true", label = "Screen plug during process", description = "Will put up a screen plug before deployment and removed it after deployment.", display = ParameterDisplay.HIDDEN, readOnly = true, checked = "true", unchecked = "false")
    }

    steps {
        PlugScreenUP()
    }

    requirements {
        equals("teamcity.agent.name", "ip_172.17.0.1")
    }
})