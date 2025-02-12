import jetbrains.buildServer.configs.kotlin.BuildSteps
import jetbrains.buildServer.configs.kotlin.buildSteps.script

fun BuildSteps.PlugScreenUP() {
    script {
        name = "PlugUP"
        scriptContent = """
                #!/bin/bash
                
                sudo bash -c "cd / && mv maintenance-stage.html2 maintenance-stage.html || echo '##teamcity[message text='<<< PAGE NOT FOUND!!! SCREEN PLUG STATE DO NOT CHANGED!!! >>>' status='WARNING']' "
                
                """.trimIndent()
    }
}

fun BuildSteps.PlugScreenDOWN() {
    script {
        name = "PlugUP"
        scriptContent = """
                #!/bin/bash
                
                sudo bash -c "cd / && mv maintenance-stage.html maintenance-stage.html2 || echo '##teamcity[message text='<<< PAGE NOT FOUND!!! SCREEN PLUG STATE DO NOT CHANGED!!! >>>' status='WARNING']' "
                
                """.trimIndent()
    }
}
