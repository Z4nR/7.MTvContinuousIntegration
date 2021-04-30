package com.zulham.mtv.utils

import com.zulham.mtv.data.Genres
import com.zulham.mtv.data.PH
import com.zulham.mtv.data.ShowEntity

object DummyData {

    fun generateDummyMovie(): ArrayList<ShowEntity>{

        val movie = ArrayList<ShowEntity>()

        movie += ShowEntity(1,
                "Wonder Women 1984",
                "December, 16 2020",
                genre = listOf(Genres("Fantastic, Action, Adventure")),
                production = listOf(PH("DC")),
                "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                "https://www.themoviedb.org/t/p/original/fN7f0sajt9uGFX4rPzQdJG3ivCr.jpg")

        movie += ShowEntity(2,
                "Peninsula",
                "July, 15 2020",
                genre = listOf(Genres("Action, Horror, Thriller")),
                production = listOf(PH("Redpeter Films")),
                "A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7S9RvfMBWSTlUZ4VbxDY7oLeenk.jpg",
                "https://www.themoviedb.org/t/p/original/rb8suuFsar9kPKXUxbPr18B4Eqm.jpg")

        movie += ShowEntity(3,
                "Stand by Me Doraemon 2",
                "November, 20 2020",
                genre = listOf(Genres("Animation")),
                production = listOf(PH("CBI Picture")),
                "She and her beloved Shizuka are finally married! Nobita\\'s childhood dream was supposed to come true on his wedding day, but for some reason, Nobita doesn\\'t show up. One of the most popular episodes from the original story transcends time and space, and leads to Nobita\\'s future. Doraemon and Nobita\\'s great adventure begins in order to fulfill his grandmother\\'s wish to see his bride at first sight. This is a story of tears and bonds, set in the past, present and future.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/mu5t94c8IZU781aMg1E1FKinYG2.jpg",
                "https://www.themoviedb.org/t/p/original/iMGd5FPajzsoEyD142pQr5wh9eS.jpg")

        movie += ShowEntity(4,
                "Avatar 2",
                "December, 15 2022",
                genre = listOf(Genres("Action, Adventure, Science Fiction, Fantasy, Thriller")),
                production = listOf(PH("20th Century Fox")),
                "Avatar: The Way of Water will once again follow Sam Worthington\\’s Jake Sully twelve years after exploring Pandora and joining the Na\\’vi. He has since raised a family with Neytiri, portrayed by Zoe Saldana, and established himself within the clans of the new world. Of course, peace can only last so long. Especially when the military organisation from the original film returns to “finish what they started”.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/110CYDvtnVRWs9meC4lsZCz98vg.jpg",
                "https://www.themoviedb.org/t/p/original/a72yJn1aW7EBvSlgAg3jtL6Zmvy.jpg")

        movie += ShowEntity(5,
                "Tenet",
                "August, 22 2020",
                genre = listOf(Genres("Action, Thriller, Science Fiction")),
                production = listOf(PH("Warner Bros")),
                "Armed with only one word - Tenet - and fighting for the survival of the entire world, the Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/k68nPLbIST6NP96JmTxmZijEvCA.jpg",
                "https://www.themoviedb.org/t/p/original/t6rKYahkbfnUpmiX8K3aHEmomDE.jpg")

        movie += ShowEntity(6,
                "Gemini Man",
                "October, 2 2019",
                genre = listOf(Genres("Science Fiction, Action, Drama, Thriller")),
                production = listOf(PH("Paramount Picture")),
                "Henry Brogan is an elite 51-year-old assassin who\\'s ready to call it quits after completing his 72nd job. His plans get turned upside down when he becomes the target of a mysterious operative who can seemingly predict his every move. To his horror, Brogan soon learns that the man who\\'s trying to kill him is a younger, faster, cloned version of himself.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/uTALxjQU8e1lhmNjP9nnJ3t2pRU.jpg",
                "https://www.themoviedb.org/t/p/original/c3F4P2oauA7IQmy4hM0OmRt2W7d.jpg")

        movie += ShowEntity(7,
                "Zack Snyder's Justice League",
                "March, 18 2021",
                genre = listOf(Genres("Action, Adventure, Fantasy, Science Fiction")),
                production = listOf(PH("Zack Snyder")),
                "Determined to ensure Superman\\'s ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                "https://www.themoviedb.org/t/p/original/zHmSuSB2Z95lurAHAknXuAo7TCC.jpg")

        movie += ShowEntity(8,
                "Tom and Jerry",
                "February, 11 2021",
                genre = listOf(Genres("Comedy, Family, Adventure")),
                production = listOf(PH("Warner Bros")),
                "Tom the cat and Jerry the mouse get kicked out of their home and relocate to a fancy New York hotel, where a scrappy employee named Kayla will lose her job if she can\\'t evict Jerry before a high-class wedding at the hotel. Her solution? Hiring Tom to get rid of the pesky mouse.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6KErczPBROQty7QoIsaa6wJYXZi.jpg",
                "https://www.themoviedb.org/t/p/original/t8wJB7BebGYck8hgbNiZXCvBgxQ.jpg")

        movie += ShowEntity(9,
                "Godzilla vs Kong",
                "March, 24 2021",
                genre = listOf(Genres("Action, Science Fiction")),
                production = listOf(PH("Legendary Picture")),
                "Legends collide as Godzilla and Kong, the two most powerful forces of nature, clash in a spectacular battle for the ages! The monster war rages on the surface and deep within our world as the spectacular secret realm of the titans known as the hollow earth is revealed",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5KYaB1CTAklQHm846mHTFkozgDN.jpg",
                "https://www.themoviedb.org/t/p/original/8iiu1VQjcH7fjKgfzVd0hlOlHXQ.jpg")

        movie += ShowEntity(10,
                "Jumanji: Level One",
                "January, 20 2021",
                genre = listOf(Genres("Adventure, Comedy")),
                production = listOf(PH("Anchors")),
                "Set in 1869, two children receive a mysterious game after their father goes missing in the jungles of Africa. They unravel both the secret of their father’s disappearance and the origin of Jumanji. See how it all began.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/mI7sIBqIsCsTjLvuiVVTfvW3FLU.jpg",
                "https://www.themoviedb.org/t/p/original/jfMprg22eDpqwpKJAVIWKLbCxm4.jpg")

        return movie

    }

    fun generateDummyTV(): ArrayList<ShowEntity>{

        val tvShow = ArrayList<ShowEntity>()

        tvShow += ShowEntity(1,
                "Alice in Borderland",
                "December, 10 2020",
                genre = listOf(Genres("Drama, Mystery, Action and Adventure")),
                production = listOf(PH("Netflix")),
                "With his two friends, a video-game-obsessed young man finds himself in a strange version of Tokyo where they must compete in dangerous games to win.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/20mOwAAPwZ1vLQkw0fvuQHiG7bO.jpg",
                "https://www.themoviedb.org/t/p/original/8edzqU74USlnfkCzHtLxILUfQW3.jpg")

        tvShow += ShowEntity(2,
                "Sweet Home",
                "December, 18 2020",
                genre = listOf(Genres("Drama, Sci-Fi and Fantasy")),
                production = listOf(PH("Netflix")),
                "Cha Hyun-Soo is a high school student. He is also a recluse and rarely leaves his room. He refuses to talk to his father, mother and younger sister. One day, his whole family, except for him, dies in a car accident. Cha Hyun-Soo is left all alone. He moves into a small apartment. At this time, a mysterious phenomenon of humans turning into monster occur all around the world. The residents of Cha Hyun-Soo\\'s apartment building, including Pyeon Sang-Wook, fight against these monsters and try to survive.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6eMg81CPLalULg8spPt2LxfQtFj.jpg",
                "https://www.themoviedb.org/t/p/original/iPnBVVlcghie2aAPMIULuVhx8CN.jpg")

        tvShow += ShowEntity(3,
                "Demon Slayer: Kimetsu no Yaiba",
                "April, 6 2019",
                genre = listOf(Genres("Animation, Drama, Sci-Fi, Fantasy, Action and Adventure")),
                production = listOf(PH("Ufotable")),
                "It is the Taishō period in Japan. Tanjirō, a kindhearted boy who sells charcoal for a living, finds his family slaughtered by a demon. To make matters worse, his younger sister Nezuko, the sole survivor, has been transformed into a demon herself. Though devastated by this grim reality, Tanjirō resolves to become a “Demon Slayer” so that he can turn his sister back into a human, and kill the demon that massacred his family.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wrCVHdkBlBWdJUZPvnJWcBRuhSY.jpg",
                "https://www.themoviedb.org/t/p/original/d1sVANghKKMZNvqjW0V6y1ejvV9.jpg")

        tvShow += ShowEntity(4,
                "Kemono Jihen",
                "January, 10 2021",
                genre = listOf(Genres("Animation, Mystery, Action & Adventure, Sci-Fi & Fantasy")),
                production = listOf(PH("")),
                "When a series of animal bodies that rot away after a single night begin appearing in a remote mountain village, Inugami, a detective from Tokyo who specializes in the occult, is called to investigate. While working the case, he befriends a strange boy who works in the field every day instead of going to school. Shunned by his peers and nicknamed “Dorotabō” after a yokai that lives in the mud, he helps Inugami uncover the truth behind the killings but supernatural forces are at work, and while Dorotabō is just a nickname, it might not be the only thing about the boy that isn\\'t human.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/ct6HkcvSGDC5yT6eQKdBn4HWcNC.jpg",
                "https://www.themoviedb.org/t/p/original/jQfH20FEhvZpnbTGpZbWPOzfAZd.jpg")

        tvShow += ShowEntity(5,
                "Haikyuu!!: To The Top",
                "January, 11 2020",
                genre = listOf(Genres("Animation, Comedy, Drama")),
                production = listOf(PH("TV Tokyo")),
                "After their triumphant victory over Shiratorizawa Academy, the Karasuno High School volleyball team has earned their long-awaited ticket to nationals. As preparations begin, genius setter Tobio Kageyama is invited to the All-Japan Youth Training Camp to play alongside fellow nationally recognized players. Meanwhile, Kei Tsukishima is invited to a special rookie training camp for first-years within the Miyagi Prefecture. Not receiving any invitations himself, the enthusiastic Shouyou Hinata feels left behind. However, Hinata does not back down. Transforming his frustration into self-motivation, he boldly decides to sneak himself into the same rookie training camp as Tsukishima. Even though Hinata only lands himself a job as the ball boy, he comes to see this as a golden opportunity. He begins to not only reflect on his skills as a volleyball player but also analyze the plethora of information available on the court and how he can apply it.",
                "https://www.themoviedb.org/t/p/original/h1JQo6FFse71EdM6yMuXy1Dtk6s.jpg",
                "https://www.themoviedb.org/t/p/original/zotzm1IzazadBQOSocTQ8Ta1bCb.jpg")

        tvShow += ShowEntity(6,
                "Jujutsu Kaisen",
                "October, 3 2020",
                genre = listOf(Genres("Animation, Action, Adventure, Sci-Fi and Fantasy")),
                production = listOf(PH("Mappa")),
                "Yuuji Itadori is a boy with tremendous physical strength, though he lives a completely ordinary high school life. One day, to save a friend who has been attacked by Curses, he eats a finger of Ryoumen Sukuna, taking the Curse into his own soul. From then on, he shares one body with Sukuna. Guided by the most powerful of sorcerers, Satoru Gojou, Itadori is admitted to the Tokyo Prefectural Jujutsu High School, an organization that fights the Curses and thus begins the heroic tale of a boy who became a Curse to exorcise a Curse, a life from which he could never turn back.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/g1rK2nRXSidcMwNliWDIroWWGTn.jpg",
                "https://www.themoviedb.org/t/p/original/lthkKBLe1rX6iThgVFg22O02sJw.jpg")

        tvShow += ShowEntity(7,
                "Mushoku Tensei: Jobless Reincarnation",
                "January, 11 2021",
                genre = listOf(Genres("Animation, Drama, Comedy, Action, Adventure, Sci-Fi and Fantasy")),
                production = listOf(PH("Studio Bing")),
                "34-year-old virgin loser is kicked out of his home by his family and realized that his life is completely over. As he regrets wasting his life, a truck runs him over and he died. When he wakes up. He\\’s in a world of sword and sorcery! Reborn as a baby named Rudeus, he decides that this time, he\\’ll live a life he won\\’t regret!",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/1mFnzilCyj9Coa9jbN8vFYLEZdW.jpg",
                "https://www.themoviedb.org/t/p/original/j9fRIimor0AMFJR9kjZubXcABzZ.jpg")

        tvShow += ShowEntity(8,
                "Attack on Titan: The Final Season",
                "December, 7 2020",
                genre = listOf(Genres("Animation, Sci-Fi, Fantasy, Action, Adventure, Drama, War, Politics and Mystery")),
                production = listOf(PH("Mappa")),
                "The truth revealed through the memories of Grisha\\'s journals shakes all of Eren\\'s deepest beliefs. There is no rugged but free land beyond the walls. There is a whole other world, equally full of oppression and war. Suddenly, the ambitions that have animated the Survey Corps for generations seem small and naive. What is there left to fight for?",
                "https://www.themoviedb.org/t/p/original/zFRjPEfN0HyWFdDacyI7KbjCkBG.jpg",
                "https://www.themoviedb.org/t/p/original/3uuS3cZCywueYBuMB3XesO9pjPS.jpg")

        tvShow += ShowEntity(9,
                "2.43: Seiin High School Boys Volleyball Team",
                "January, 8 2021",
                genre = listOf(Genres("Animation, Drama")),
                production = listOf(PH("David Production")),
                "Kimichika Haijima was a strong junior high school volleyball team member. After getting into trouble with the team, he moved to his mother\\'s hometown and reunites with his childhood friend, Yuni Kuroba. The two become an ace pair at volleyball, but in their last tournament, get into a big fight. Will they ever play volleyball together again?",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/utYAqKUQdUOVVQlM2FJBTWdONf9.jpg",
                "https://www.themoviedb.org/t/p/original/r9KwK1lKX2xFVwNKKDgKZMuRKxd.jpg")

        tvShow += ShowEntity(10,
                "WandaVision",
                "January, 15 2021",
                genre = listOf(Genres("Sci-Fi, Fantasy, Action, Adventure, Mystery and Comedy")),
                production = listOf(PH("Marvel Studios")),
                "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
                "https://www.themoviedb.org/t/p/original/1i1N0AVRb54H6ZFPDTwbo9MLxSF.jpg")

        return tvShow

    }

}