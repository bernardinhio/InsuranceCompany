package bernardo.bernardinhio.globalside.model

class HealthPrompt(
    var message: String = "",
    var title: String = "",
    var category: String = "",
    var isPermanent: Boolean = false,
    var imageUrl: String = "",
    var imagePrimaryColor: String = "#FFFFFF",
    var imageSecondaryColor: String = "#FFFFFF"
)