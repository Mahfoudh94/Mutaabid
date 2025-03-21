package net.rebbat.mutaabid

import android.icu.util.IslamicCalendar
import android.icu.util.TimeZone as IcuTimeZone
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone

suspend fun awaitAll(vararg blocks: suspend () -> Unit) = coroutineScope {
    blocks.forEach {
        launch { it() }
    }
}

fun IslamicCalendar.toLocalDate(): LocalDate {
    val day = this.get(IslamicCalendar.DAY_OF_MONTH)
    val month = this.get(IslamicCalendar.MONTH)
    val year = this.get(IslamicCalendar.YEAR)

    return LocalDate(year, month, day)
}

fun IslamicCalendar.defaultZone(): IslamicCalendar {
    return this.apply {
        val tz = TimeZone.currentSystemDefault().id
        this.timeZone = IcuTimeZone.getTimeZone(tz)
    }
}

val IslamicCalendar.YEAR: Int get() = this.get(IslamicCalendar.YEAR)
val IslamicCalendar.MONTH: Int get() = this.get(IslamicCalendar.MONTH)
val IslamicCalendar.DAY: Int get() = this.get(IslamicCalendar.DAY_OF_MONTH)
val IslamicCalendar.DAY_OF_WEEK: Int get() = this.get(IslamicCalendar.DAY_OF_WEEK)
val IslamicCalendar.DAYS_IN_MONTH: Int get() = this.get(IslamicCalendar.IS_LEAP_MONTH).plus(29)

fun LocalDate.toIslamicCalendar(): IslamicCalendar {
    return IslamicCalendar(this.year, this.monthNumber, this.dayOfMonth).apply {
        val tz = TimeZone.UTC.id
        this.timeZone = IcuTimeZone.getTimeZone(tz)
    }
}

val IslamicMonthsNames = arrayOf(
    "Muharram",
    "Safar",
    "Rabii Al Awal",
    "Rabii Al Thani",
    "Jumada Al Awal",
    "Jumada Al Thani",
    "Rajab",
    "Shaaban",
    "Ramadan",
    "Shauwal",
    "Dhu Al Qi'dah",
    "Dhu Al Hijjah"
)

fun _t(text: String) : String? {
    val text_map = mapOf(
        "QuranRead.title" to "ورد قراءة القرآن",
        "QuranRead.desc" to "ينير القلب ويزيد الحسنات ويبعث الطمأنينة.",
        "QuranListen.title" to "ورد سماع القرآن",
        "QuranListen.desc" to "يقوي التدبر ويزيد الخشوع ويشرح الصدر.",
        "NiyahRenew.title" to "تجديد النية",
        "NiyahRenew.desc" to "يجعل الأعمال خالصة لله عز وجل.",
        "Subh.title" to "صلاة الصبح + أذكار",
        "Subh.desc" to "خير مايبتدأ به المسلم يومه.",
        "AdhkarSabah.title" to "أذكار الصباح",
        "AdhkarSabah.desc" to "تمنح البركة في اليوم وتحفظ المسلم.",
        "Dhuha.title" to "صلاة الضحى",
        "Dhuha.desc" to "صلاة للأوابين في وقت ينشغل الناس بأعمالهم.",
        "Fasting.title" to "الصيام تطوعا أو فرضا",
        "Fasting.desc" to "من خير العبادات وأجزلها أجرا.",
        "Sadaka.title" to "صدقة",
        "Sadaka.desc" to "ما نقص مال من صدقة.",
        "Kahf.title" to "سورة الكهف",
        "Kahf.desc" to "أضاء له من النور مابين الجمعتين.",
        "PreDuhr.title" to "نافلة الظهر القبلية",
        "PreDuhr.desc" to "ركعتان أو أربع قبل الظهر.",
        "Duhr.title" to "صلاة الظهر",
        "Duhr.desc" to "إن الصلاة تنهى عن الفحشاء والمنكر.",
        "PostDuhr.title" to "نافلة الظهر البعدية",
        "PostDuhr.desc" to "ركعتان بعد الظهر.",
        "Asr.title" to "صلاة العصر",
        "Asr.desc" to "حافظوا على الصلاة والصلاة الوسطى.",
        "DuaaMaghrib.title" to "دعاء قبل المغرب",
        "DuaaMaghrib.desc" to "للصائم دعوة لاترد.",
        "AdhkarMasa.title" to "أذكار المساء",
        "AdhkarMasa.desc" to "تحفظ المسلم وتنزل البركة بمسائه.",
        "Maghrib.title" to "صلاة المغرب",
        "Maghrib.desc" to "كان ﷺ يعجل بأدائها.",
        "PostMaghrib.title" to "نافلة المغرب",
        "PostMaghrib.desc" to "ركعتان بعيد الصلاة.",
        "Isha.title" to "صلاة العشاء",
        "Isha.desc" to "ثقيلة على المنافق.",
        "Witr.title" to "الوتر",
        "Witr.desc" to "اجعلوا آخر صلاتكم وترا.",
        "Kiyam.title" to "قيام الليل",
        "Kiyam.desc" to "أحب الصلاة إلى الله صلاة داود.",
        "AdhkarNaum.title" to "أذكار ما قبل النوم",
        "AdhkarNaum.desc" to "تجعلك في حفظ الله.",
        "TaharaNaum.title" to "النوم على طهارة",
        "TaharaNaum.desc" to "يجعل الله لك ملكا يدعو لك.",
        "IslamKnowledge.title" to "معلومة دينية",
        "IslamKnowledge.desc" to "من يرد الله به خيرا يفقهه في الدين.",

        "DayName.1" to "الأحد",
        "DayName.2" to "الإثنين",
        "DayName.3" to "الثلاثاء",
        "DayName.4" to "الأربعاء",
        "DayName.5" to "الخميس",
        "DayName.6" to "الجمعة",
        "DayName.7" to "السبت",

        "MonthName.0" to "محرم",
        "MonthName.1" to "صفر",
        "MonthName.2" to "ربيع الأول",
        "MonthName.3" to "ربيع الثاني",
        "MonthName.4" to "جمادى الأولى",
        "MonthName.5" to "جمادى الثاني",
        "MonthName.6" to "رجب",
        "MonthName.7" to "شعبان",
        "MonthName.8" to "رمضان",
        "MonthName.9" to "شوال",
        "MonthName.10" to "ذو القعدة",
        "MonthName.11" to "ذو الحجة",

        "ManageWirds.text" to "إدارة الورد",
    )

    return text_map[text]
}
