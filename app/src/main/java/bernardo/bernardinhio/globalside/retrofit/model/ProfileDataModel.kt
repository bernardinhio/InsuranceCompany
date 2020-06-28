package bernardo.bernardinhio.globalside.retrofit.model

class ProfileDataModel {
    var tariff_id: String? = null
    var tariff_label: String? = null
    var address: Address? = null
    var gender: String? = null
    var date_of_birth: String? = null
    var last_name: String? = null
    var profile_attributes: Array<String>? = null
    var display_name: String? = null
    var is_primary_profile: Boolean? = null
    var policy_number: String? = null
    var profile_id: String? = null
    var contact: Contact? = null
    var tariff: Tariff? = null
    var first_name: String? = null

    inner class Contact {
        var phone: String? = null
        var email: String? = null
    }

    inner class Address {
        var zip: String? = null
        var city: String? = null
        var street: String? = null
        var street_number: String? = null
    }

    inner class Tariff {
        var excess_rate: String? = null
        var icon: Icon? = null
        var id: String? = null
        var label: String? = null

        inner class Icon {
            var secondary_color: String? = null
            var primary_color: String? = null
            var url: String? = null
        }
    }
}