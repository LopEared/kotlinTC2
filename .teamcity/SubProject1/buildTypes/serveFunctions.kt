import jetbrains.buildServer.configs.kotlin.BuildSteps
import jetbrains.buildServer.configs.kotlin.buildSteps.script

fun BuildSteps.PlugScreenUP() {
    script {
        name = "Plug Turn ON"
        id = "PlugTurnON_STEP"
        workingDir = "/"
        scriptContent = """
            echo "Test Moving PlugPage.html"
            cd / && mv maintenance-stage.html2 maintenance-stage.html || echo "##teamcity[message text='<<< PAGE NOT FOUND!!! SCREEN PLUG STATE DO NOT CHANGED!!! >>>' status='WARNING']"
            sleep 10
            """.trimIndent()
    }
}

fun BuildSteps.PlugScreenDOWN() {
    script {
        name = "Plug Turn OFF"
        id = "PlugTurnOFF_STEP"
        workingDir = "/"
        scriptContent = """
            echo "Test Moving PlugPage.html"
            cd / && mv maintenance-stage.html maintenance-stage.html2 || echo "##teamcity[message text='<<< PAGE NOT FOUND!!! SCREEN PLUG STATE DO NOT CHANGED!!! >>>' status='WARNING']"
            sleep 10
            """.trimIndent()
    }
}
