import jetbrains.buildServer.configs.kotlin.BuildSteps
import jetbrains.buildServer.configs.kotlin.BuildType
import jetbrains.buildServer.configs.kotlin.BuildTypeSettings.Type.REGULAR

object FrontendPlugScreenOn : BuildType({
    name = "FrontendPlugScreenOn"

    type = REGULAR

    vcs {
        excludeDefaultBranchChanges = true
        showDependenciesChanges = true
    }

    steps {
        frontendPlugScreenUP()
    }
})

private fun BuildSteps.frontendPlugScreenUP() {
    step {
        script {
            name = "Quasi Turn On"
            id = "QuasiTurnOn"
            workingDir = "/"
            scriptContent = """
                    #!/bin/bash
                    mv maintenance-stage.html2 maintenance-stage.html || exit 1
                    """.trimIndent()
        }
    }
}
