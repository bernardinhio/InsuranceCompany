package bernardo.bernardinhio.globalside.retrofit.model

class ProfileHealthPromptDataModel {
    var metadata: Metadata? = null
    var permanent: String? = null
    private var style: Style? = null
    var message: String? = null
    var uuid: String? = null
    var display_category: String? = null

    inner class Metadata {
        private var link: Link? = null

        inner class Link {
            private var title: String? = null
            private var url: String? = null
        }
    }

    inner class Style {
        private var image: String? = null
        private var secondary_color: String? = null
        private var primary_color: String? = null
    }

}