import jetbrains.buildServer.configs.kotlin.BuildSteps
import jetbrains.buildServer.configs.kotlin.buildSteps.script

// fun BuildSteps.PlugScreenUP() {
//     script {
//         name = "Plug Turn ON"
//         id = "PlugTurnON_STEP"
//         workingDir = "/"
//         scriptContent = """
//             #!/bin/bash
//             if [ -f /maintenance-stage.html2 ]; then
//                 echo "TURN ON PLUG SCREEN"
//                 bash -c "cd / && mv maintenance-stage.html2 maintenance-stage.html || exit 1"
//             else
//                 echo "##teamcity[message text='PAGE FILE NOT FOUND!!! SCREEN PLUG STATE DO NOT CHANGED!!!' status='WARNING']"
//             fi
//             """.trimIndent()
//     }
// }

fun BuildSteps.PlugScreenUP() {
    script {
        name = "Plug Turn ON"
        id = "PlugTurnON_STEP"
        workingDir = "/"
        scriptContent = """
            #!/bin/bash
            echo "Test Pass CHECK BOX value in BASH"
            if [[ "%TestCheckBox%" == "true" ]]; then
                echo "CHECK BOX FLAG -> OK!"
                if [ -f /maintenance-stage.html2 ]; then
                    echo "TURN ON PLUG SCREEN"
                    bash -c "cd / && mv maintenance-stage.html2 maintenance-stage.html || exit 1"
                else
                    echo "##teamcity[message text='PAGE FILE NOT FOUND!!! SCREEN PLUG STATE DO NOT CHANGED!!!' status='WARNING']"
                fi
            else
                echo "CHECK BOX FLAG -> NOT OK! ACTION SKIPPED!!!"
            fi
            """.trimIndent()
    }
}

private fun BuildSteps.pgsqlBackup() {
    step {
        name = "Create psql backup"
        scriptContent = """
                #!/bin/bash
                if [[ "%pgsqlMakeBackup%" == "true" ]]; then
                    find /testDir -type f -mmin +5 -name '*' -execdir rm -- '{}'
                else
                    echo "SKIPPED: Make Backup for Postgres DB consult and appoinment."
                fi
        """.trimIndent()
    }
}

fun BuildSteps.PlugScreenDOWN() {
    script {
        name = "Plug Turn OFF"
        id = "PlugTurnOFF_STEP"
        workingDir = "/"
        scriptContent = """
            #!/bin/bash
            echo "Test Pass CHECK BOX value in BASH"
            if [[ "%TestCheckBox%" == "true" ]]; then
                echo "CHECK BOX FLAG -> OK!"
                if [ -f /maintenance-stage.html ]; then
                    echo "TURN OFF PLUG SCREEN"
                    bash -c "cd / && mv maintenance-stage.html maintenance-stage.html2 || exit 1"
                else
                    echo "##teamcity[message text='PAGE FILE NOT FOUND!!! SCREEN PLUG STATE DO NOT CHANGED!!!' status='WARNING']"
                fi
            else
                echo "CHECK BOX FLAG -> NOT OK! ACTION SKIPPED!!!"
            fi
            """.trimIndent()
    }
}

// fun BuildSteps.PlugScreenDOWN() {
//     script {
//         name = "Plug Turn OFF"
//         id = "PlugTurnOFF_STEP"
//         workingDir = "/"
//         scriptContent = """
//             #!/bin/bash
//             if [ -f /maintenance-stage.html ]; then
//                 echo "TURN OFF PLUG SCREEN"
//                 bash -c "cd / && mv maintenance-stage.html maintenance-stage.html2 || exit 1"
//             else
//                 echo "##teamcity[message text='PAGE FILE NOT FOUND!!! SCREEN PLUG STATE DO NOT CHANGED!!!' status='WARNING']"
//             fi
//             """.trimIndent()
//     }
// }

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