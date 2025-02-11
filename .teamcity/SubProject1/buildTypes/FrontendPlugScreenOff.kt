package Deploy.STAGE.buildTypes

import Deploy.STAGE.buildTypes.StageCopyCommon.sshExec
import jetbrains.buildServer.configs.kotlin.BuildSteps
import jetbrains.buildServer.configs.kotlin.BuildType
import jetbrains.buildServer.configs.kotlin.BuildTypeSettings.Type.REGULAR

object FrontendPlugScreenOff : BuildType({
    name = "FrontendPlugScreenOff"

    type = REGULAR

    vcs {
        excludeDefaultBranchChanges = true
        showDependenciesChanges = true
    }

    steps {
        frontendPlugScreenDown("30222")
        frontendPlugScreenDown("36022")
    }
})

private fun BuildSteps.frontendPlugScreenDown(port: String) {
    step {
        name = "Service Plugs DOWN"
        sshExec(
            port, """
                    sudo bash -c "cd /etc/nginx/html/ && mv maintenance-stage.html maintenance-stage.html2 || exit 1"
                """
        )
    }
}
