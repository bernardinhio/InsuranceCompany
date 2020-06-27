package bernardo.bernardinhio.globalside.retrofit.model

class ProfileHealthPromptDataModel {
    var metadata: Metadata? = null
    var permanent: String? = null
    var style: Style? = null
    var message: String? = null
    var uuid: String? = null
    var display_category: String? = null

    inner class Metadata {
        var link: Link? = null

        inner class Link {
            var title: String? = null
            var url: String? = null
        }
    }

    inner class Style {
        var image: String? = null
        var secondary_color: String? = null
        var primary_color: String? = null
    }

}