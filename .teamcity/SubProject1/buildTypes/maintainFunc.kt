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

fun BuildSteps.createFile() {
    script {
        name = "Create Test Condition File"
        workingDir = "/"
        scriptContent = """
            echo "StartCreate File"
            touch %build.number%_conditionFile.txt
            sleep 5
            """.trimIndent()
    }
}

fun BuildSteps.testPassCheckBoxinBash() {
    script {
        name = "Test Pass CheckBox Value into Bash Script"
        workingDir = "/"
        scriptContent = """
            #!/bin/bash
            echo "Test Pass CHECK BOX value in BASH"
            if [[ "%TestCheckBox%" == "true" ]]; then
                echo "CHECK BOX FLAG -> OK!" && touch /checkBoxFile.txt
            else
                echo "CHECK BOX FLAG -> NOT OK!!!" && rm -f /checkBoxFile.txt
            fi
            """.trimIndent()
    }
}