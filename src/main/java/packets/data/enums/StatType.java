package packets.data.enums;

/**
 * Ordinal of stats.
 */
public enum StatType {
    MAX_HP_STAT(0),
    HP_STAT(1),
    SIZE_STAT(2),
    MAX_MP_STAT(3),
    MP_STAT(4),
    NEXT_LEVEL_EXP_STAT(5),
    EXP_STAT(6),
    LEVEL_STAT(7),
    INVENTORY_0_STAT(8),
    INVENTORY_1_STAT(9),
    INVENTORY_2_STAT(10),
    INVENTORY_3_STAT(11),
    INVENTORY_4_STAT(12),
    INVENTORY_5_STAT(13),
    INVENTORY_6_STAT(14),
    INVENTORY_7_STAT(15),
    INVENTORY_8_STAT(16),
    INVENTORY_9_STAT(17),
    INVENTORY_10_STAT(18),
    INVENTORY_11_STAT(19),
    ATTACK_STAT(20),
    DEFENSE_STAT(21),
    SPEED_STAT(22),
    UNKNOWN23(23),
    SEASONAL(24),
    SKIN_ID(25),
    VITALITY_STAT(26),
    WISDOM_STAT(27),
    DEXTERITY_STAT(28),
    CONDITION_STAT(29),
    NUM_STARS_STAT(30),
    NAME_STAT(31),
    TEX1_STAT(32),
    TEX2_STAT(33),
    MERCHANDISE_TYPE_STAT(34),
    CREDITS_STAT(35),
    MERCHANDISE_PRICE_STAT(36),
    ACTIVE_STAT(37),
    ACCOUNT_ID_STAT(38),
    FAME_STAT(39),
    MERCHANDISE_CURRENCY_STAT(40),
    CONNECT_STAT(41),
    MERCHANDISE_COUNT_STAT(42),
    MERCHANDISE_MINS_LEFT_STAT(43),
    MERCHANDISE_DISCOUNT_STAT(44),
    MERCHANDISE_RANK_REQ_STAT(45),
    MAX_HP_BOOST_STAT(46),
    MAX_MP_BOOST_STAT(47),
    ATTACK_BOOST_STAT(48),
    DEFENSE_BOOST_STAT(49),
    SPEED_BOOST_STAT(50),
    VITALITY_BOOST_STAT(51),
    WISDOM_BOOST_STAT(52),
    DEXTERITY_BOOST_STAT(53),
    OWNER_ACCOUNT_ID_STAT(54),
    RANK_REQUIRED_STAT(55),
    NAME_CHOSEN_STAT(56),
    CURR_FAME_STAT(57),
    NEXT_CLASS_QUEST_FAME_STAT(58),
    LEGENDARY_RANK_STAT(59),
    SINK_LEVEL_STAT(60),
    ALT_TEXTURE_STAT(61),
    GUILD_NAME_STAT(62),
    GUILD_RANK_STAT(63),
    BREATH_STAT(64),
    XP_BOOSTED_STAT(65),
    XP_TIMER_STAT(66),
    LD_TIMER_STAT(67),
    LT_TIMER_STAT(68),
    HEALTH_POTION_STACK_STAT(69),
    MAGIC_POTION_STACK_STAT(70),
    BACKPACK_0_STAT(71),
    BACKPACK_1_STAT(72),
    BACKPACK_2_STAT(73),
    BACKPACK_3_STAT(74),
    BACKPACK_4_STAT(75),
    BACKPACK_5_STAT(76),
    BACKPACK_6_STAT(77),
    BACKPACK_7_STAT(78),
    HASBACKPACK_STAT(79),
    TEXTURE_STAT(80),
    PET_INSTANCEID_STAT(81),
    PET_NAME_STAT(82),
    PET_TYPE_STAT(83),
    PET_RARITY_STAT(84),
    PET_MAXABILITYPOWER_STAT(85),
    PET_FAMILY_STAT(86),
    PET_FIRSTABILITY_POINT_STAT(87),
    PET_SECONDABILITY_POINT_STAT(88),
    PET_THIRDABILITY_POINT_STAT(89),
    PET_FIRSTABILITY_POWER_STAT(90),
    PET_SECONDABILITY_POWER_STAT(91),
    PET_THIRDABILITY_POWER_STAT(92),
    PET_FIRSTABILITY_TYPE_STAT(93),
    PET_SECONDABILITY_TYPE_STAT(94),
    PET_THIRDABILITY_TYPE_STAT(95),
    NEW_CON_STAT(96),
    FORTUNE_TOKEN_STAT(97),
    SUPPORTER_POINTS_STAT(98),
    SUPPORTER_STAT(99),
    CHALLENGER_STARBG_STAT(100),
    PLAYER_ID(101),
    PROJECTILE_SPEED_MULT(102),
    PROJECTILE_LIFE_MULT(103),
    OPENED_AT_TIMESTAMP(104),
    EXALTED_ATT(105),
    EXALTED_DEF(106),
    EXALTED_SPEED(107),
    EXALTED_VIT(108),
    EXALTED_WIS(109),
    EXALTED_DEX(110),
    EXALTED_HP(111),
    EXALTED_MP(112),
    EXALTATION_BONUS_DAMAGE(113),
    EXALTATION_IC_REDUCTION(114),
    GRAVE_ACCOUNT_ID(115),
    POTION_ONE_TYPE(116),
    POTION_TWO_TYPE(117),
    POTION_THREE_TYPE(118),
    POTION_BELT(119),
    FORGEFIRE(120),
    UNKNOWN121(121),
    UNKNOWN122(122),
    UNKNOWN123(123),
    UNKNOWN124(124),
    ANIMATION_ID(125),
    UNKNOWN126(126);

    private final int index;

    StatType(int i) {
        index = i;
    }

    public int get() {
        return index;
    }

    public static StatType byOrdinal(int ord) {
        for (StatType o : StatType.values()) {
            if (o.index == ord) {
                return o;
            }
        }
        return null;
    }
}
