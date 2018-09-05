package maxdistructo.discord.bots.botfather.commands.mafia.methods

import java.awt.Color
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.MessageEmbed
import java.time.Instant

object RoleCards {
    fun onRoleCardAsk(role : String) : MessageEmbed{
        return onRoleCardAsk(null, role, null)
    }
    fun onRoleCardAsk(message: Message?, role: String, user: Member?): MessageEmbed {
        val builder = EmbedBuilder()
        builder.setFooter("Botfather","https://images-ext-2.discordapp.net/external/FFfeb7Bn2NOWtVsTQxpeEhIa2w3BOlutl7NbuzMuzvc/%3Fsize%3D128/https/cdn.discordapp.com/avatars/423268575718014976/00ec188230511b48cee42c5831c03b45.jpg")
        builder.setTimestamp(Instant.now())
        val townColor = Color(107,198,23)
        val mafiaColor = Color(223,2,5)
        val amneColor = Color(27,232,221)
        val survColor = Color(202,204,85)
        val vampColor = Color(123,137,105)
        val arsoColor = Color(235,130,17)
        val skColor = Color(10,9,132)
        val wwColor = Color(130,80,55)
        val witchColor = Color(193,95,255)
        val gaColor = Color(235,245,241)
        val juggColor = Color(128,30,75)
        val pirateColor = Color(221,174,97)
        val plagueColor = Color(206,251,99)
        val pestColor = Color(16,18,18)

        when (role) {
            "investigator" -> {
                builder.addField("Class", "Town Investigative (TI)", true)
                builder.addField("Goal", "Lynch every criminal or evildoer", true)
                builder.addField("Attack: 0 - Defence: 0", "Your allies are Survivors, Pirates, Guardian Angels, and other Town members", false)
                builder.addField("Summary", "A private eye who secretly gathers information.", false)
                builder.setAuthor("Investigator", "https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/2/20/Achievement_Investigator.png/revision/latest/scale-to-width-down/50?cb=20140825150920")
                builder.setThumbnail("https://vignette.wikia.nocookie.net/town-of-salem/images/c/cb/Investigator.png/revision/latest?cb=20141222203926")
                builder.setDescription("Investigate one person each night for a clue to their role.")
                builder.setColor(townColor)

            }
            "sheriff" -> {
                builder.addField("Class", "Town Investigative (TI)", true)
                builder.addField("Goal", "Lynch every criminal or evildoer", true)
                builder.addField("Attack: 0 - Defence: 0", "Your allies are Survivors, Pirates, Guardian Angels, and other Town members", false)
                builder.addField("Summary", "The law enforcer of the Town, forced into hiding from threat of murder.", false);
                builder.setAuthor("Sheriff", "https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/e/e7/Achievement_Sheriff.png/revision/latest/scale-to-width-down/50?cb=20140825150706")
                builder.setThumbnail("https://vignette.wikia.nocookie.net/town-of-salem/images/3/3e/Sheriff.png/revision/latest/scale-to-width-down/150?cb=20140802032529")
                builder.setDescription("Interrogate one person each night for suspicious activity.")
                builder.setColor(townColor)

            }
            "spy" -> {
                builder.addField("Class", "Town Investigative (TI)", true)
                builder.addField("Goal", "Lynch every criminal or evildoer", true)
                builder.addField("Attack: 0 - Defence: 0", "Your allies are Survivors, Pirates, Guardian Angels, and other Town members", false)
                builder.addField("Summary", "A talented watcher who keeps track of evil in the Town.", false);
                builder.setAuthor("Spy", "https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/0/0b/Achievement_Spy.png/revision/latest/scale-to-width-down/50?cb=20140825150715")
                builder.setThumbnail("https://vignette.wikia.nocookie.net/town-of-salem/images/3/36/Spy.png/revision/latest/scale-to-width-down/150?cb=20151129195522")
                builder.setDescription("You are able to read whispers and the chat of the mafia at night. You will also know who the mafia visits.")
                builder.setColor(townColor)
            }
            "lookout" -> {

                builder.addField("Class", "Town Investigative (TI)", true)
                builder.addField("Goal", "Lynch every criminal or evildoer", true)
                builder.addField("Attack: 0 - Defence: 0", "Your allies are Survivors, Pirates, Guardian Angels, and other Town members", false)
                builder.addField("Summary", "An eagle-eyed observer, stealthily camping outside houses to gain information.", false);
                builder.setAuthor("Lookout", "https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/f/f6/Achievement_Lookout.png/revision/latest/scale-to-width-down/50?cb=20140825150631")
                //builder.setThumbnail("https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg")
                builder.setDescription("Watch one person at night to see who visits them.")
                builder.setColor(townColor)

            }
            "vigilante" -> {

                builder.addField("Class", "Town Killing (TK)", true)
                builder.addField("Goal", "Lynch every criminal or evildoer", true)
                builder.addField("Attack: 1 - Defence: 0", "Your allies are Survivors, Pirates, Guardian Angels, and other Town members", false)
                builder.addField("Summary", "A militant officer willing to bend the law to enact justice.", false);
                builder.setAuthor("Vigilante", "https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/1/1f/Achievement_Vigilante.png/revision/latest?cb=20140825150808")
                builder.setThumbnail("https://vignette.wikia.nocookie.net/town-of-salem/images/3/3c/Brock_Smith.png/revision/latest/scale-to-width-down/138?cb=20160914033426")
                builder.setDescription("Choose to take justice into your own hands and shoot someone. You only have 3 shots and if you kill another town member, you will suicide.")
                builder.setColor(townColor)

            }
            "veteran" -> {

                builder.addField("Class", "Town Killing (TK)", true)
                builder.addField("Goal", "Lynch every criminal or evildoer", true)
                builder.addField("Attack: 2 - Defence: 0", "Your allies are Survivors, Pirates, Guardian Angels, and other Town members", false)
                builder.addField("Summary", "A paranoid war hero who will shoot anyone who visits him.", false);
                builder.setAuthor("Veteran", "https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/1/1b/Achievement_Veteran.png/revision/latest/scale-to-width-down/50?cb=20140825150759")
                builder.setDescription("Decide if you will go on alert. You can go on alert 3 times killing all who visit you. When you are on alert, you will have a defence of 1 or general immunity.")
                builder.setColor(townColor)
            }
            "vampire_hunter" -> {

                builder.addField("Class", "Town Killing (TK)", true)
                builder.addField("Goal", "Lynch every criminal or evildoer", true)
                builder.addField("Attack: 1 - Defence: 0", "Your allies are Survivors, Pirates, Guardian Angels, and other Town members", false)
                builder.addField("Summary", "A priest turned monster hunter, this person slays Vampires.", false);
                builder.setAuthor("Vampire Hunter", "https://vignette.wikia.nocookie.net/town-of-salem/images/8/8e/Achievement_Vampire_Hunter.png/revision/latest/scale-to-width-down/50?cb=20151130210939")
                builder.setThumbnail("https://vignette.wikia.nocookie.net/town-of-salem/images/c/c8/Vampire_Hunter.png/revision/latest/scale-to-width-down/150?cb=20151101133904")
                builder.setDescription("Check for Vampires each night. If you find a Vampire you will attack them. If a Vampire visits you, you will attack them. You can hear Vampires at night. If all the Vampires die you will become a Vigilante.")
                builder.setColor(townColor)

            }
            "jailor" -> {

                builder.addField("Class", "Town Killing (TK)", true)
                builder.addField("Goal", "Lynch every criminal or evildoer", true)
                builder.addField("Attack: 2 - Defence: 0", "Your allies are Survivors, Pirates, Guardian Angels, and other Town members", false)
                builder.addField("Summary", "A prison guard who secretly detains suspects.", false)
                builder.setAuthor("Jailor", "https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/6/64/Achievement_Jailor.png/revision/latest/scale-to-width-down/50?cb=20140825150602")
                builder.setThumbnail("https://vignette.wikia.nocookie.net/town-of-salem/images/7/7e/Jailor.png/revision/latest/scale-to-width-down/150?cb=20151021224315")
                builder.setDescription("You may choose one person during the day to jail for the night. You may anonymously talk with your prisoner. You can choose to attack your prisoner. The jailed target can't perform their night ability.")
                builder.setColor(townColor)
            }
            "bodyguard" -> {

                builder.addField("Class", "Town Protective (TP)", true)
                builder.addField("Goal", "Lynch every criminal or evildoer", true)
                builder.addField("Attack: 2 - Defence: 0", "Your allies are Survivors, Pirates, Guardian Angels, and other Town members", false)
                builder.addField("Summary", "An ex-soldier who secretly makes a living by selling protection.", false)
                builder.setAuthor("Bodyguard","https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg", "https://vignette.wikia.nocookie.net/town-of-salem/images/e/ef/Achievement_Bodyguard.png/revision/latest/scale-to-width-down/50?cb=20140708090613")
                builder.setThumbnail("https://vignette.wikia.nocookie.net/town-of-salem/images/8/87/Bodyguard.png/revision/latest?cb=20150207170237")
                builder.setDescription("Protect a player from direct attacks at night. If your target is attacked or is the victim of a harmful visit, you and the visitor will fight. If you successfully protect someone you can still be Healed.")
                builder.setColor(townColor)
            }
            "doctor" -> {

                builder.addField("Class", "Town Protective (TP)", true)
                builder.addField("Goal", "Lynch every criminal or evildoer", true)
                builder.addField("Attack: 0 - Defence: 0", "Your allies are Survivors, Pirates, Guardian Angels, and other Town members", false)
                builder.addField("Summary", "A surgeon skilled in trauma care who secretly heals people.", false)
                builder.setAuthor("Doctor", "https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/a/a0/Achievement_Doctor.png/revision/latest/scale-to-width-down/50?cb=20140708093156")
                builder.setThumbnail("https://vignette.wikia.nocookie.net/town-of-salem/images/6/6c/Doctor.png/revision/latest?cb=20140802032512")
                builder.setDescription("Heal one person each night, preventing them from dying. You may only Heal yourself once. You will know if your target is attacked.")
                builder.setColor(townColor)
            }
            "escort" -> {

                builder.addField("Class", "Town Support (TS)", true)
                builder.addField("Goal", "Lynch every criminal or evildoer", true)
                builder.addField("Attack: 0 - Defence: 0", "Your allies are Survivors, Pirates, Guardian Angels, and other Town members", false)
                builder.addField("Summary", "A beautiful woman skilled in distraction.", false)
                builder.setAuthor("Escort", "https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/2/29/Achievement_Escort.png/revision/latest/scale-to-width-down/50?cb=20140708093747")
                builder.setThumbnail("https://vignette.wikia.nocookie.net/town-of-salem/images/d/d3/Escort.png/revision/latest?cb=20150506224645")
                builder.setDescription("Distract someone each night. Distraction blocks your target from using their role's night ability. You cannot be role blocked.")
                builder.setColor(townColor)
            }
            "mayor" -> {

                builder.addField("Class", "Town Support (TS)", true)
                builder.addField("Goal", "Lynch every criminal or evildoer", true)
                builder.addField("Attack: 0 - Defence: 0", "Your allies are Survivors, Pirates, Guardian Angels, and other Town members", false)
                builder.addField("Summary", "The leader of the Town.", false)
                builder.setAuthor("Mayor", "https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/a/aa/Achievement_Mayor_2017.png/revision/latest/scale-to-width-down/50?cb=20171130202502")
                builder.setThumbnail("https://vignette.wikia.nocookie.net/town-of-salem/images/8/8b/MoneyBag.png/revision/latest?cb=20141029221203")
                builder.setDescription("You may reveal yourself as the Mayor of the Town. Once you have revealed yourself as Mayor your vote counts as 3 votes. You may not be healed once you have revealed yourself. Once revealed, you can't whisper or be whispered to.")
                builder.setColor(townColor)
            }
            "medium" -> {

                builder.addField("Class", "Town Support (TS)", true)
                builder.addField("Goal", "Lynch every criminal or evildoer", true)
                builder.addField("Attack: 0 - Defence: 0", "Your allies are Survivors, Pirates, Guardian Angels, and other Town members", false)
                builder.addField("Summary", "A secret Psychic who talks with the dead.", false)
                builder.setAuthor("Medium", "https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/6/63/Achievement_Medium.png/revision/latest/scale-to-width-down/50?cb=20140825150649")
                builder.setThumbnail("https://i.imgur.com/WBTx4Kx.png")
                builder.setDescription("You can speak to the dead and they can speak to you. All messages you send to the dead are anonymous. Once you die, you are able to tell the town one message.")
                builder.setColor(townColor)
            }
            "retributionist" -> {

                builder.addField("Class", "Town Support (TS)", true)
                builder.addField("Goal", "Lynch every criminal or evildoer", true)
                builder.addField("Attack: 0 - Defence: 0", "Your allies are Survivors, Pirates, Guardian Angels, and other Town members", false)
                builder.addField("Summary", "A powerful mystic who will give one person a second chance at life.", false)
                builder.setAuthor("Retributionist", "https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/6/61/Achievement_Retributionist.png/revision/latest/scale-to-width-down/50?cb=20140825150657")
                builder.setThumbnail("https://vignette.wikia.nocookie.net/town-of-salem/images/6/66/Nightspirit174%27s_Retributionist_Avatar.png/revision/latest?cb=20160423042204")
                builder.setDescription("You may revive 1 dead Town member.")
                builder.setColor(townColor)
            }
            "transporter" -> {

                builder.addField("Class", "Town Support (TS)", true)
                builder.addField("Goal", "Lynch every criminal or evildoer", true)
                builder.addField("Attack: 0 - Defence: 0", "Your allies are Survivors, Pirates, Guardian Angels, and other Town members", false)
                builder.addField("Summary", "A man who transports people without asking any questions.", false)
                builder.setAuthor("Transporter","https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/5/5a/Achievement_Transporter.png/revision/latest/scale-to-width-down/50?cb=20140825150750")
                builder.setThumbnail("https://vignette.wikia.nocookie.net/town-of-salem/images/6/66/Nightspirit174%27s_Retributionist_Avatar.png/revision/latest?cb=20160423042204")
                builder.setDescription("Choose two people to transport at night. Transporting two people swaps all targets against them. You may transport yourself. Your targets will know they were transported. You may not transport someone with themselves. You can not transport Jailed people.")
                builder.setColor(townColor)
            }
            "blackmailer" -> {

                builder.addField("Class", "Mafia Support (MS)", true)
                builder.addField("Goal", "Kill anyone who doesn't submit to Mafia.", true)
                builder.addField("Attack: 0 - Defence: 0", "Your allies are Survivors, Pirates, Guardian Angels, Witches, and other Mafia Members.", false)
                builder.addField("Summary", "An eavesdropper who uses information to keep people quiet.", false)
                builder.setAuthor("Blackmailer","https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/0/0f/Achievement_Blackmailer.png/revision/latest?cb=20140825150350")
                builder.setThumbnail("https://orig00.deviantart.net/3959/f/2016/043/f/0/blackmailer_skin_5_by_purplellamas_town_of_salem_by_purplellamas5-d9ri8cf.png")
                builder.setDescription("Choose one person each night to blackmail. Blackmailed targets can not talk during the day. You can hear private messages.")
                builder.setColor(mafiaColor)
            }
            "consigliere" -> {

                builder.addField("Class", "Mafia Support (MS)", true)
                builder.addField("Goal", "Kill anyone who doesn't submit to Mafia.", true)
                builder.addField("Attack: 0 - Defence: 0", "Your allies are Survivors, Pirates, Guardian Angels, Witches, and other Mafia Members.", false)
                builder.addField("Summary", "A corrupted Investigator who gathers information for the Mafia.", false)
                builder.setAuthor("Consigliere","https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/f/f6/Achievement_Consigliere.png/revision/latest?cb=20140825150405")
                builder.setThumbnail("https://orig00.deviantart.net/3959/f/2016/043/f/0/blackmailer_skin_5_by_purplellamas_town_of_salem_by_purplellamas5-d9ri8cf.png")
                builder.setDescription("Check one person for their exact role each night.")
                builder.setColor(mafiaColor)

            }
            "consort" -> {

                builder.addField("Class", "Mafia Support (MS)", true)
                builder.addField("Goal", "Kill anyone who doesn't submit to Mafia.", true)
                builder.addField("Attack: 0 - Defence: 0", "Your allies are Survivors, Pirates, Guardian Angels, Witches, and other Mafia Members.", false)
                builder.addField("Summary", "A beautiful dancer working for organized crime.", false)
                builder.setAuthor("Consort","https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/f/f4/Achievement_Consort.png/revision/latest/scale-to-width-down/50?cb=20140825150414")
                builder.setThumbnail("http://town-of-salem.wikia.com/wiki/File:Escort.png")
                builder.setDescription("Distract someone each night. Distraction blocks your target from using their role's night ability.")
                builder.setColor(mafiaColor)

            }
            "disguiser" -> {

                builder.addField("Class", "Mafia Deception (MD)", true)
                builder.addField("Goal", "Kill anyone who doesn't submit to Mafia.", true)
                builder.addField("Attack: 0 - Defence: 0", "Your allies are Survivors, Pirates, Guardian Angels, Witches, and other Mafia Members.", false)
                builder.addField("Summary", "A master of disguise who pretends to be other roles.", false)
                builder.setAuthor("Disguiser","https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/b/bf/Achievement_Disguiser.png/revision/latest/scale-to-width-down/50?cb=20140825150509")
                builder.setThumbnail("https://vignette.wikia.nocookie.net/town-of-salem/images/e/e4/NasubiNori_Disguiser_Avatar.png/revision/latest?cb=20180311074829")
                builder.setDescription("Choose a target to disguise yourself as. You will appear to be the role of your target to the Investigator. If you are killed you will appear to be the role of your target.")
                builder.setColor(mafiaColor)

            }
            "forger" -> {

                builder.addField("Class", "Mafia Deception (MD)", true)
                builder.addField("Goal", "Kill anyone who doesn't submit to Mafia.", true)
                builder.addField("Attack: 0 - Defence: 0", "Your allies are Survivors, Pirates, Guardian Angels, Witches, and other Mafia Members.", false)
                builder.addField("Summary", "A crooked lawyer that replaces documents.", false)
                builder.setAuthor("Forger","https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg", "https://vignette.wikia.nocookie.net/town-of-salem/images/f/f0/Achievement_Forger.png/revision/latest/scale-to-width-down/50?cb=20150801143107")
                builder.setThumbnail("https://vignette.wikia.nocookie.net/town-of-salem/images/6/66/ForgerAvatar.png/revision/latest?cb=20150724030632")
                builder.setDescription("If you chose to use your ability, the next person the mafia kills will get an improper response. You only have 3 uses of this role.")

                builder.setColor(mafiaColor)
            }
            "framer" -> {

                builder.addField("Class", "Mafia Deception (MD)", true)
                builder.addField("Goal", "Kill anyone who doesn't submit to Mafia.", true)
                builder.addField("Attack: 0 - Defence: 0", "Your allies are Survivors, Pirates, Guardian Angels, Witches, and other Mafia Members.", false)
                builder.addField("Summary", "A skilled counterfeiter who manipulates information.", false)
                builder.setAuthor("Framer", "https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/5/5c/Achievement_Framer.png/revision/latest/scale-to-width-down/50?cb=20140825150526")
                builder.setThumbnail("https://vignette.wikia.nocookie.net/town-of-salem/images/6/66/ForgerAvatar.png/revision/latest?cb=20150724030632")
                builder.setDescription("Choose someone to frame at night. If your target is investigated they will appear to be a member of the Mafia.")
                builder.setColor(mafiaColor)

            }
            "janitor" -> {

                builder.addField("Class", "Mafia Deception (MD)", true)
                builder.addField("Goal", "Kill anyone who doesn't submit to Mafia.", true)
                builder.addField("Attack: 0 - Defence: 0", "Your allies are Survivors, Pirates, Guardian Angels, Witches, and other Mafia Members.", false)
                builder.addField("Summary", "A sanitation expert working for organized crime.", false)
                builder.setAuthor("Janitor","https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/1/1a/Achievement_Janitor.png/revision/latest/scale-to-width-down/50?cb=20140825150612")
                builder.setThumbnail("https://vignette.wikia.nocookie.net/town-of-salem/images/4/4d/Yuan_Itor.png/revision/latest/scale-to-width-down/154?cb=20160826042531")
                builder.setDescription("Choose a person to clean at night. If they die, their role will not be revealed to the town. You will know their role though. You only have 3 uses of this ability.")
                builder.setColor(mafiaColor)

            }
            "godfather" -> {

                builder.addField("Class", "Mafia Killing (MK)", true)
                builder.addField("Goal", "Kill anyone who doesn't submit to Mafia.", true)
                builder.addField("Attack: 1 - Defence: 0", "Your allies are Survivors, Pirates, Guardian Angels, Witches, and other Mafia Members.", false)
                builder.addField("Summary", "The leader of organized crime.", false)
                builder.setAuthor("Godfather","https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/b/b1/Achievement_Godfather.png/revision/latest/scale-to-width-down/50?cb=20140825150541")
                builder.setThumbnail("https://vignette.wikia.nocookie.net/town-of-salem/images/9/97/Godfather_2.png/revision/latest/scale-to-width-down/100?cb=20160606042812")
                builder.setDescription("You decide who the mafia will kill. If you are role blocked, the Mafioso's kill will be used instead of your own. You are unable to be found by a Sheriff.")

                builder.setColor(mafiaColor)
            }
            "mafioso" -> {

                builder.addField("Class", "Mafia Killing (MK)", true)
                builder.addField("Goal", "Kill anyone who doesn't submit to Mafia.", true)
                builder.addField("Attack: 1 - Defence: 0", "Your allies are Survivors, Pirates, Guardian Angels, Witches, and other Mafia Members.", false)
                builder.addField("Summary", "A member of organized crime, trying to work their way to the top.", false)
                builder.setAuthor("Mafioso","https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/7/70/Achievement_Mafioso.png/revision/latest/scale-to-width-down/50?cb=20140825150640")
                builder.setThumbnail("https://vignette.wikia.nocookie.net/town-of-salem/images/7/70/DarkRevenant.png/revision/latest/scale-to-width-down/87?cb=20140701002425")
                builder.setDescription("You may choose a target to attack. If the Godfather chooses his own, then you will attack the Godfather's target instead. If the Godfather dies, you will be promoted to Godfather.")

                builder.setColor(mafiaColor)
            }
            "amnesiac" -> {

                builder.addField("Class", "Neutral Benign (NB)", true)
                builder.addField("Goal", "Remember a role and complete that role's goal.", true)
                builder.addField("Attack: 0 - Defence: 0", "You have no allies until you remember a role.", false)
                builder.addField("Summary", "A trauma patient that does not remember who he was.", false)
                builder.setAuthor("Amnesiac","https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/6/65/Achievement_Amnesiac.png/revision/latest/scale-to-width-down/50?cb=20140825150322")
                builder.setThumbnail("https://vignette.wikia.nocookie.net/town-of-salem/images/2/2f/Forgetful_Freddy.png/revision/latest/scale-to-width-down/110?cb=20160826030733")
                builder.setDescription("Remember who you were by selecting a dead person")
                builder.setColor(amneColor)

            }
            "survivor" -> {

                builder.addField("Class", "Neutral Benign (NB)", true)
                builder.addField("Goal", "Survive to the end of the game.", true)
                builder.addField("Attack: 0 - Defence: 0", "Everyone is your ally if they let you survive.", false)
                builder.addField("Summary", "A Neutral character who just wants to live.", false)
                builder.setAuthor("Survivor","https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/5/57/Achievement_Survivor.png/revision/latest/scale-to-width-down/50?cb=20140825150726")
                builder.setThumbnail("https://orig00.deviantart.net/8843/f/2016/008/a/1/survivor_avatar_for_tos_by_nasubinori-d9n6u8b.png")
                builder.setDescription("You may use a vest protecting yourself. Each of 4 vests can only be used once.")
                builder.setColor(survColor)

            }
            "executioner" -> {

                builder.addField("Class", "Neutral Evil (NE)", true)
                builder.addField("Goal", "Get your target lynched", true)
                builder.addField("Attack: 0 - Defence: 0", "Everyone can be your ally as long as your goal is completed.", false)
                builder.addField("Summary", "An obsessed lyncher who will stop at nothing to execute his target.", false)
                builder.setAuthor("Executioner","https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/8/8c/Achievement_Executioner.png/revision/latest/scale-to-width-down/50?cb=20140825150517")
                builder.setThumbnail("https://orig00.deviantart.net/8843/f/2016/008/a/1/survivor_avatar_for_tos_by_nasubinori-d9n6u8b.png")
                builder.setDescription("Trick the Town into lynching your target. If your target gets killed by anyone else, you will become a Jester.")
                builder.setColor(Color.LIGHT_GRAY)

            }
            "jester" -> {

                builder.addField("Class", "Neutral Evil (NE)", true)
                builder.addField("Goal", "Get lynched by the town", true)
                builder.addField("Attack: 0 - Defence: 0", "Everyone can be your ally as long as your goal is completed.", false)
                builder.addField("Summary", "A crazed lunatic whose life goal is to be publicly executed.", false)
                builder.setAuthor("Jester","https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/0/05/Achievement_Jester.png/revision/latest/scale-to-width-down/50?cb=20140825150623")
                builder.setThumbnail("https://vignette.wikia.nocookie.net/town-of-salem/images/e/e0/Jester.png/revision/latest?cb=20140716035330")
                builder.setDescription("Trick the town into lynching you.")
                builder.setColor(Color.PINK)

            }
            "witch" -> {

                builder.addField("Class", "Neutral Evil (NE)", true)
                builder.addField("Goal", "Survive to see the Town lose the game.", true)
                builder.addField("Attack: 0 - Defence: 0", "Everyone but the town can be your ally", false)
                builder.addField("Summary", "A voodoo master who can control other people's actions.", false)
                builder.setAuthor("Witch", "https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/2/20/Achievement_Witch.png/revision/latest/scale-to-width-down/50?cb=20140825150816")
                builder.setThumbnail("https://vignette.wikia.nocookie.net/town-of-salem/images/e/e6/Witch.png/revision/latest?cb=20140716035354")
                builder.setDescription("Control someone each night. You can force people to target themselves. Your victim will know they are being controlled. You will know the role of the player you control.")
                builder.setColor(witchColor)

            }
            "arsonist" -> {

                builder.addField("Class", "Neutral Killing (NK)", true)
                builder.addField("Goal", "See everyone burn.", true)
                builder.addField("Attack: 3 - Defence: 1", "Survivor, Witch, Pirate, Guardian Angels, and other Arsonists are your allies.", false)
                builder.addField("Summary", "A pyromaniac that wants to burn everyone.", false)
                builder.setAuthor("Arsonist","https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/c/cf/Achievement_Arsonist.png/revision/latest/scale-to-width-down/50?cb=20140825150335")
                builder.setThumbnail("https://vignette.wikia.nocookie.net/town-of-salem/images/c/c3/Arsonist.png/revision/latest?cb=20141029221117")
                builder.setDescription("You may Douse someone in gasoline or ignite Doused targets. If you are doused and do no action one night, you will clean off the gas from another Arsonist.")
                builder.setColor(arsoColor)

            }
            "serial_killer" -> {

                builder.addField("Class", "Neutral Killing (NK)", true)
                builder.addField("Goal", "Kill everyone who would oppose you.", true)
                builder.addField("Attack: 1 - Defence: 1", "Survivor, Witch, Pirate, Guardian Angels, and other Serial Killers are your allies.", false)
                builder.addField("Summary", "A psychotic criminal who wants everyone to die.", false)
                builder.setAuthor("Serial Killer", "https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/9/98/Achievement_Serial_Killer.png/revision/latest/scale-to-width-down/50?cb=20140723234035")
                builder.setThumbnail("https://vignette.wikia.nocookie.net/town-of-salem/images/7/75/SerialKiller.png/revision/latest?cb=20140816021322")
                builder.setDescription("You may kill a target every night. If someone tries to roleblock you, they will die. If you are Jailed and not executed, you will kill the Jailor.")
                builder.setColor(skColor)

            }
            "werewolf" -> {

                builder.addField("Class", "Neutral Killing (NK)", true)
                builder.addField("Goal", "Kill everyone who would oppose you.", true)
                builder.addField("Attack: 2 - Defence: 1", "Survivor, Witch, Pirate, Guardian Angels, and other Werewolves are your allies.", false)
                builder.addField("Summary", "A normal citizen who transforms during the full moon.", false)
                builder.setAuthor("Werewolf","https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/0/07/Achievement_Werewolf2.png/revision/latest?cb=20170730212305")
                builder.setThumbnail("https://vignette.wikia.nocookie.net/town-of-salem/images/c/c1/Lycanthrope.png/revision/latest/scale-to-width-down/151?cb=20160506214350")
                builder.setDescription("You will go on a rampage every other night. Going on a rampage means that all people who visit your target will die. If you do not chose a target, then all people who visit you will die")
                builder.setColor(wwColor)
            }
            "vampire" -> {
                builder.addField("Class", "Neutral Chaos (NC)", true)
                builder.addField("Goal", "Convert everyone who would oppose you.", true)
                builder.addField("Attack: 1 - Defence: 0", "Survivor, Witch, Pirate, Guardian Angels, and other Vampires are your allies.", false)
                builder.addField("Summary", "Someone among the undead who turns others at night.", false)
                builder.setAuthor("Vampire","https://i.ytimg.com/vi/7t2j3uiLs4g/maxresdefault.jpg","https://vignette.wikia.nocookie.net/town-of-salem/images/7/75/Achievement_Vampire.png/revision/latest/scale-to-width-down/50?cb=20151130211326")
                builder.setThumbnail("https://vignette.wikia.nocookie.net/town-of-salem/images/4/4e/Vampire.png/revision/latest/scale-to-width-down/150?cb=20151101133009")
                builder.setDescription("Every other night you may bite someone who is not already a Vampire. If this person is a Vampire Hunter, you will die. If this person is a Mafia Member, they will die. Your bite does not go through Night Immunity.")
                builder.setColor(vampColor)
            }
        }
        return builder.build()
    }
}