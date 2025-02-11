import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.BuildSteps
import jetbrains.buildServer.configs.kotlin.BuildType
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.BuildTypeSettings.Type.REGULAR

object FrontendPlugScreenOn : BuildType({
    name = "FrontendPlugScreenOn"

    type = REGULAR

    vcs {
        root(DslContext.settingsRoot)
        excludeDefaultBranchChanges = true
        showDependenciesChanges = true
    }

    steps {
        frontendPlugScreenUP()
    }
})

private fun BuildSteps.frontendPlugScreenUP() {
    script {
        name = "Quasi Turn On"
        id = "QuasiTurnOn"
        workingDir = "/"
        scriptContent = """
                #!/bin/bash
                cd / && mv maintenance-stage.html2 maintenance-stage.html || exit 1
                """.trimIndent()
    }
}
