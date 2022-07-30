package com.dicoding.jlegends.members

import com.dicoding.jlegends.R

object LegendsData {

    private val data = arrayOf(
            arrayOf(
                    "Jake Wharton",
                    "Google, Inc",
                    R.drawable.user1,
                    "Pittsburgh, PA, USA",
                    ": JakeWharton\n"+
                            ": 102 \n"+
                            ": 56995 \n"+
                            ": 12"
            ),
            arrayOf(
                    "Amit Shekhar",
                    "MindOrksOpenSource",
                    R.drawable.user2,
                    "New Delhi, India",
                    ": amitshekhariitbhu\n"+
                            ": 37\n"+
                            ": 5153 \n"+
                            ": 2"
            ),
            arrayOf(
                    "Romain Guy",
                    "Google",
                    R.drawable.user3,
                    "California",
                    ": romainguy\n"+
                            ": 9\n"+
                            ": 7972\n"+
                            ": 0"
            ),
            arrayOf(
                    "Chris Banes",
                    "Google working on @android",
                    R.drawable.user4,
                    "Sydney, Australia",
                    ": chrisbanes\n"+
                            ": 30\n"+
                            ": 14725\n"+
                            ": 1"
            ),
            arrayOf(
                    "David",
                    "Working Group Two",
                    R.drawable.user5,
                    "Trondheim, Norway",
                    ": tipsy\n"+
                            ": 56\n"+
                            ": 788 \n"+
                            ": 0"
            ),
            arrayOf(
                    "Ravi Tamada",
                    "AndroidHive | Droid5",
                    R.drawable.user6,
                    "India",
                    ": ravi8x\n"+
                            ": 28\n"+
                            ": 18628\n"+
                            ": 3"
            ),
            arrayOf(
                    "Deny Prasetyo",
                    "gojek-engineering",
                    R.drawable.user7,
                    "Kotagede, Yogyakarta, Indonesia",
                    ": jasoet\n"+
                            ": 44\n"+
                            ": 277\n"+
                            ": 39"
            ),
            arrayOf(
                    "Budi Oktaviyan",
                    "KotlinID",
                    R.drawable.user8,
                    "Jakarta, Indonesia",
                    ": budioktaviyan\n"+
                            ": 110\n"+
                            ": 178 \n"+
                            ": 23"
            ),
            arrayOf(
                    "Hendi Santika",
                    "JVMDeveloperID @KotlinID @IDDevOps",
                    R.drawable.user9,
                    "Bojongsoang - Bandung Jawa Barat",
                    ": hendisantika\n"+
                            ": 1064\n"+
                            ": 428 \n"+
                            ": 61"
            ),
            arrayOf(
                    "Sidiq Permana",
                    "Nusantara Beta Studio",
                    R.drawable.user10,
                    "Jakarta Indonesia",
                    ": sidiqpermana\n"+
                            ": 65\n"+
                            ": 465 \n"+
                            ": 10"
            )
    )

    val listData: ArrayList<Legends>
        get() {
            val list = ArrayList<Legends>()
            for (data in data) {
                val legends = Legends()
                legends.name = data[0] as String
                legends.area = data[1] as String
                legends.photo = data[2] as Int
                legends.overview = data[3] as String
                legends.detail = data[4] as String
                list.add(legends)
            }
            return list
        }
}