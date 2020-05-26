import com.electriccloud.client.groovy.ElectricFlow
import com.electriccloud.client.groovy.models.ActualParameter


/*
parameters:
listOfProjects - Text Area
targetDestinationPath - /path/to/file
CreateBackupOfProject - ExportDSL Plugin Procedure
*/

class exportDSL{
    def doExport(){
        ElectricFlow ef = new ElectricFlow()
        println "Start to do the export"
        def textArea = '''$[listOfProjects]'''
        println "Gathering list of projects:"
        println textArea
        def listOfProjects = textArea.split("\\r?\\n")
        println "List is: $listOfProjects"
        for (def project in listOfProjects){
            def params = [
                    new ActualParameter (actualParameterName: 'targetDestinationPath', value: '$[targetDestinationPath]'),
                    new ActualParameter (actualParameterName: 'projectName', value: "$project"),
            ]
            ef.createJobStep(subprocedure: "CreateBackupOfProject", actualParameters: params)
        }
    }
}

def edsl = new exportDSL()

edsl.doExport()