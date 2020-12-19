package de.tn8.music;

public class Songs {

    public static final int[] MELODY = {
            Notes.NOTES.get("E7"), Notes.NOTES.get("E7"), 0, Notes.NOTES.get("E7"),
            0, Notes.NOTES.get("C7"), Notes.NOTES.get("E7"), 0,
            Notes.NOTES.get("G7"), 0, 0, 0,
            Notes.NOTES.get("G6"), 0, 0, 0,

            Notes.NOTES.get("C7"), 0, 0, Notes.NOTES.get("G6"),
            0, 0, Notes.NOTES.get("E6"), 0,
            0, Notes.NOTES.get("A6"), 0, Notes.NOTES.get("B6"),
            0, Notes.NOTES.get("AS6"), Notes.NOTES.get("A6"), 0,

            Notes.NOTES.get("G6"), Notes.NOTES.get("E7"), Notes.NOTES.get("G7"),
            Notes.NOTES.get("A7"), 0, Notes.NOTES.get("F7"), Notes.NOTES.get("G7"),
            0, Notes.NOTES.get("E7"), 0, Notes.NOTES.get("C7"),
            Notes.NOTES.get("D7"), Notes.NOTES.get("B6"), 0, 0,

            Notes.NOTES.get("C7"), 0, 0, Notes.NOTES.get("G6"),
            0, 0, Notes.NOTES.get("E6"), 0,
            0, Notes.NOTES.get("A6"), 0, Notes.NOTES.get("B6"),
            0, Notes.NOTES.get("AS6"), Notes.NOTES.get("A6"), 0,

            Notes.NOTES.get("G6"), Notes.NOTES.get("E7"), Notes.NOTES.get("G7"),
            Notes.NOTES.get("A7"), 0, Notes.NOTES.get("F7"), Notes.NOTES.get("G7"),
            0, Notes.NOTES.get("E7"), 0, Notes.NOTES.get("C7"),
            Notes.NOTES.get("D7"), Notes.NOTES.get("B6"), 0, 0
    };

    public static final int[] TEMPO = {
            12, 12, 12, 12,
            12, 12, 12, 12,
            12, 12, 12, 12,
            12, 12, 12, 12,

            12, 12, 12, 12,
            12, 12, 12, 12,
            12, 12, 12, 12,
            12, 12, 12, 12,

            9, 9, 9,
            12, 12, 12, 12,
            12, 12, 12, 12,
            12, 12, 12, 12,

            12, 12, 12, 12,
            12, 12, 12, 12,
            12, 12, 12, 12,
            12, 12, 12, 12,

            9, 9, 9,
            12, 12, 12, 12,
            12, 12, 12, 12,
            12, 12, 12, 12
    };


    public static final int[] UNDERWORLD_MELODY = {
            Notes.NOTES.get("C4"), Notes.NOTES.get("C5"), Notes.NOTES.get("A3"), Notes.NOTES.get("A4"),
            Notes.NOTES.get("AS3"), Notes.NOTES.get("AS4"), 0,
            0,
            Notes.NOTES.get("C4"), Notes.NOTES.get("C5"), Notes.NOTES.get("A3"), Notes.NOTES.get("A4"),
            Notes.NOTES.get("AS3"), Notes.NOTES.get("AS4"), 0,
            0,
            Notes.NOTES.get("F3"), Notes.NOTES.get("F4"), Notes.NOTES.get("D3"), Notes.NOTES.get("D4"),
            Notes.NOTES.get("DS3"), Notes.NOTES.get("DS4"), 0,
            0,
            Notes.NOTES.get("F3"), Notes.NOTES.get("F4"), Notes.NOTES.get("D3"), Notes.NOTES.get("D4"),
            Notes.NOTES.get("DS3"), Notes.NOTES.get("DS4"), 0,
            0, Notes.NOTES.get("DS4"), Notes.NOTES.get("CS4"), Notes.NOTES.get("D4"),
            Notes.NOTES.get("CS4"), Notes.NOTES.get("DS4"),
            Notes.NOTES.get("DS4"), Notes.NOTES.get("GS3"),
            Notes.NOTES.get("G3"), Notes.NOTES.get("CS4"),
            Notes.NOTES.get("C4"), Notes.NOTES.get("FS4"), Notes.NOTES.get("F4"), Notes.NOTES.get("E3"), Notes.NOTES.get("AS4"), Notes.NOTES.get("A4"),
            Notes.NOTES.get("GS4"), Notes.NOTES.get("DS4"), Notes.NOTES.get("B3"),
            Notes.NOTES.get("AS3"), Notes.NOTES.get("A3"), Notes.NOTES.get("GS3"),
            0, 0, 0
    };

    public static final int[] UNDERWORLD_TEMPO = {
            12, 12, 12, 12,
            12, 12, 6,
            3,
            12, 12, 12, 12,
            12, 12, 6,
            3,
            12, 12, 12, 12,
            12, 12, 6,
            3,
            12, 12, 12, 12,
            12, 12, 6,
            6, 18, 18, 18,
            6, 6,
            6, 6,
            6, 6,
            18, 18, 18, 18, 18, 18,
            10, 10, 10,
            10, 10, 10,
            3, 3, 3
    };

    public static final int[] ADVENTURE_TIME_MELODY = {
    Notes.NOTES.get("D5"),
    Notes.NOTES.get("G5"),Notes.NOTES.get("G5"),Notes.NOTES.get("G5"),Notes.NOTES.get("G5"),Notes.NOTES.get("FS5"),
    Notes.NOTES.get("FS5"),Notes.NOTES.get("E5"),Notes.NOTES.get("D5"),Notes.NOTES.get("E5"),Notes.NOTES.get("D5"),Notes.NOTES.get("D5"),
    Notes.NOTES.get("C5"),Notes.NOTES.get("B5"),Notes.NOTES.get("A5"),Notes.NOTES.get("G4"),
            0,Notes.NOTES.get("C5"),Notes.NOTES.get("B5"),Notes.NOTES.get("A5"),Notes.NOTES.get("G4"),0,
    Notes.NOTES.get("G5"),0,Notes.NOTES.get("G5"),Notes.NOTES.get("G5"),0,Notes.NOTES.get("G5"),
    Notes.NOTES.get("FS5"),0,Notes.NOTES.get("E5"),Notes.NOTES.get("E5"),Notes.NOTES.get("D5"),Notes.NOTES.get("D5"),
    Notes.NOTES.get("C5"),Notes.NOTES.get("C5"),Notes.NOTES.get("C5"),Notes.NOTES.get("D5"),
    Notes.NOTES.get("D5"),Notes.NOTES.get("A5"),Notes.NOTES.get("B5"),Notes.NOTES.get("A5"),Notes.NOTES.get("G4"),
    Notes.NOTES.get("G5")
};
    
    public static final int[] ADVENTURE_TIME_TEMPO = {24,
                    24,12,12,12,24,
                    12,24,24,24,12,24,
                    12,12,12,12,
                    24,12,24,24,12,24,
                    24,24,24,12,24,12,
                    24,24,24,12,12,24,
                    8,24,24,8,
                    8,24,12,24,24,
                    12
};


    public static final int [] STAR_WARS_MELODY = {
            Notes.NOTES.get("G4"), Notes.NOTES.get("G4"), Notes.NOTES.get("G4"),
            Notes.NOTES.get("EB4"), 0, Notes.NOTES.get("BB4"), Notes.NOTES.get("G4"),
            Notes.NOTES.get("EB4"), 0, Notes.NOTES.get("BB4"), Notes.NOTES.get("G4"), 0,

            Notes.NOTES.get("D4"), Notes.NOTES.get("D4"), Notes.NOTES.get("D4"),
            Notes.NOTES.get("EB4"), 0, Notes.NOTES.get("BB3"), Notes.NOTES.get("FS3"),
            Notes.NOTES.get("EB3"), 0, Notes.NOTES.get("BB3"), Notes.NOTES.get("G3"), 0,

            Notes.NOTES.get("G4"), 0, Notes.NOTES.get("G3"), Notes.NOTES.get("G3"), 0,
            Notes.NOTES.get("G4"), 0, Notes.NOTES.get("FS4"), Notes.NOTES.get("F4"),
            Notes.NOTES.get("E4"), Notes.NOTES.get("EB4"), Notes.NOTES.get("E4"), 0,
            Notes.NOTES.get("GS3"), Notes.NOTES.get("CS3"), 0,

            Notes.NOTES.get("C3"), Notes.NOTES.get("B3"), Notes.NOTES.get("BB3"), Notes.NOTES.get("A3"), Notes.NOTES.get("BB3"), 0,
            Notes.NOTES.get("EB3"), Notes.NOTES.get("FS3"), Notes.NOTES.get("EB3"), Notes.NOTES.get("FS3"),
            Notes.NOTES.get("BB3"), 0, Notes.NOTES.get("G3"), Notes.NOTES.get("BB3"), Notes.NOTES.get("D4"), 0,


            Notes.NOTES.get("G4"), 0, Notes.NOTES.get("G3"), Notes.NOTES.get("G3"), 0,
            Notes.NOTES.get("G4"), 0, Notes.NOTES.get("FS4"), Notes.NOTES.get("F4"),
            Notes.NOTES.get("E4"), Notes.NOTES.get("EB4"), Notes.NOTES.get("E4"), 0,
            Notes.NOTES.get("GS3"), Notes.NOTES.get("CS3"), 0,

            Notes.NOTES.get("C3"), Notes.NOTES.get("B3"), Notes.NOTES.get("BB3"), Notes.NOTES.get("A3"), Notes.NOTES.get("BB3"), 0,

            Notes.NOTES.get("EB3"), Notes.NOTES.get("FS3"), Notes.NOTES.get("EB3"),
            Notes.NOTES.get("BB3"), Notes.NOTES.get("G3"), Notes.NOTES.get("EB3"), 0, Notes.NOTES.get("BB3"), Notes.NOTES.get("G3"),
    };


    public static final int []  STAR_WARS_TEMPO ={
            2,2,2,
            4,8,6,2,
            4,8,6,2,8,

            2,2,2,
            4,8,6,2,
            4,8,6,2,8,

            2,16,4,4,8,
            2,8,4,6,
            6,4,4,8,
            4,2,8,
            4,4,6,4,2,8,
            4,2,4,4,
            2,8,4,6,2,8,

            2,16,4,4,8,
            2,8,4,6,
            6,4,4,8,
            4,2,8,
            4,4,6,4,2,8,
            4,2,2,
            4,2,4,8,4,2
};

    public static final int []  POPCORN_MELODY ={

    Notes.NOTES.get("A4"),Notes.NOTES.get("G4"),Notes.NOTES.get("A4"),Notes.NOTES.get("E4"),Notes.NOTES.get("C4"),Notes.NOTES.get("E4"),Notes.NOTES.get("A3"),
    Notes.NOTES.get("A4"),Notes.NOTES.get("G4"),Notes.NOTES.get("A4"),Notes.NOTES.get("E4"),Notes.NOTES.get("C4"),Notes.NOTES.get("E4"),Notes.NOTES.get("A3"),

    Notes.NOTES.get("A4"),Notes.NOTES.get("B4"),Notes.NOTES.get("C5"),Notes.NOTES.get("B4"),Notes.NOTES.get("C5"),Notes.NOTES.get("A4"),Notes.NOTES.get("B4"),Notes.NOTES.get("A4"),Notes.NOTES.get("B4"),Notes.NOTES.get("G4"),
    Notes.NOTES.get("A4"),Notes.NOTES.get("G4"),Notes.NOTES.get("A4"),Notes.NOTES.get("F4"),Notes.NOTES.get("A4"),


    Notes.NOTES.get("A4"),Notes.NOTES.get("G4"),Notes.NOTES.get("A4"),Notes.NOTES.get("E4"),Notes.NOTES.get("C4"),Notes.NOTES.get("E4"),Notes.NOTES.get("A3"),
    Notes.NOTES.get("A4"),Notes.NOTES.get("G4"),Notes.NOTES.get("A4"),Notes.NOTES.get("E4"),Notes.NOTES.get("C4"),Notes.NOTES.get("E4"),Notes.NOTES.get("A3"),

    Notes.NOTES.get("A4"),Notes.NOTES.get("B4"),Notes.NOTES.get("C5"),Notes.NOTES.get("B4"),Notes.NOTES.get("C5"),Notes.NOTES.get("A4"),Notes.NOTES.get("B4"),Notes.NOTES.get("A4"),Notes.NOTES.get("B4"),Notes.NOTES.get("G4"),
    Notes.NOTES.get("A4"),Notes.NOTES.get("G4"),Notes.NOTES.get("A4"),Notes.NOTES.get("B4"),Notes.NOTES.get("C5"),

    Notes.NOTES.get("E5"),Notes.NOTES.get("D5"),Notes.NOTES.get("E5"),Notes.NOTES.get("C5"),Notes.NOTES.get("G4"),Notes.NOTES.get("C5"),Notes.NOTES.get("E4"),
    Notes.NOTES.get("E5"),Notes.NOTES.get("D5"),Notes.NOTES.get("E5"),Notes.NOTES.get("C5"),Notes.NOTES.get("G4"),Notes.NOTES.get("C5"),Notes.NOTES.get("E4"),

    Notes.NOTES.get("E5"),Notes.NOTES.get("FS5"),Notes.NOTES.get("G5"),Notes.NOTES.get("FS5"),Notes.NOTES.get("G5"),Notes.NOTES.get("E5"),Notes.NOTES.get("FS5"),Notes.NOTES.get("E5"),Notes.NOTES.get("FS5"),Notes.NOTES.get("D5"),
    Notes.NOTES.get("E5"),Notes.NOTES.get("D5"),Notes.NOTES.get("E5"),Notes.NOTES.get("C5"),Notes.NOTES.get("E5"),

    Notes.NOTES.get("E5"),Notes.NOTES.get("D5"),Notes.NOTES.get("E5"),Notes.NOTES.get("C5"),Notes.NOTES.get("G4"),Notes.NOTES.get("C5"),Notes.NOTES.get("E4"),
    Notes.NOTES.get("E5"),Notes.NOTES.get("D5"),Notes.NOTES.get("E5"),Notes.NOTES.get("C5"),Notes.NOTES.get("G4"),Notes.NOTES.get("C5"),Notes.NOTES.get("E4"),

    Notes.NOTES.get("E5"),Notes.NOTES.get("FS5"),Notes.NOTES.get("G5"),Notes.NOTES.get("FS5"),Notes.NOTES.get("G5"),Notes.NOTES.get("E5"),Notes.NOTES.get("FS5"),Notes.NOTES.get("E5"),Notes.NOTES.get("FS5"),Notes.NOTES.get("D5"),
    Notes.NOTES.get("E5"),Notes.NOTES.get("D5"),Notes.NOTES.get("B4"),Notes.NOTES.get("D5"),Notes.NOTES.get("E5")
};

    public static final int []    POPCORN_TEMPO = {
            8, 8, 8, 8, 8, 8, 4,
            8, 8, 8, 8, 8, 8, 4,

            8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
            8, 8, 8, 8, 4,

            8, 8, 8, 8, 8, 8, 4,
            8, 8, 8, 8, 8, 8, 4,

            8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
            8, 8, 8, 8, 4,

            8, 8, 8, 8, 8, 8, 4,
            8, 8, 8, 8, 8, 8, 4,

            8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
            8, 8, 8, 8, 4,

            8, 8, 8, 8, 8, 8, 4,
            8, 8, 8, 8, 8, 8, 4,

            8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
            8, 8, 8, 8, 4
    };

    public static final int []    TWINKLE_TWINKLE_MELODY = {
            Notes.NOTES.get("C4"), Notes.NOTES.get("C4"), Notes.NOTES.get("G4"), Notes.NOTES.get("G4"), Notes.NOTES.get("A4"), Notes.NOTES.get("A4"), Notes.NOTES.get("G4"),
            Notes.NOTES.get("F4"), Notes.NOTES.get("F4"), Notes.NOTES.get("E4"), Notes.NOTES.get("E4"), Notes.NOTES.get("D4"), Notes.NOTES.get("D4"), Notes.NOTES.get("C4"),

            Notes.NOTES.get("G4"), Notes.NOTES.get("G4"), Notes.NOTES.get("F4"), Notes.NOTES.get("F4"), Notes.NOTES.get("E4"), Notes.NOTES.get("E4"), Notes.NOTES.get("D4"),
            Notes.NOTES.get("G4"), Notes.NOTES.get("G4"), Notes.NOTES.get("F4"), Notes.NOTES.get("F4"), Notes.NOTES.get("E4"), Notes.NOTES.get("E4"), Notes.NOTES.get("D4"),

            Notes.NOTES.get("C4"), Notes.NOTES.get("C4"), Notes.NOTES.get("G4"), Notes.NOTES.get("G4"), Notes.NOTES.get("A4"), Notes.NOTES.get("A4"), Notes.NOTES.get("G4"),
            Notes.NOTES.get("F4"), Notes.NOTES.get("F4"), Notes.NOTES.get("E4"), Notes.NOTES.get("E4"), Notes.NOTES.get("D4"), Notes.NOTES.get("D4"), Notes.NOTES.get("C4")
    };

    public static final int[] TWINKLE_TWINKLE_TEMPO = {
            4,4,4,4,4,4,2,
                    4,4,4,4,4,4,2,

                    4,4,4,4,4,4,2,
                    4,4,4,4,4,4,2,

                    4,4,4,4,4,4,2,
                    4,4,4,4,4,4,2
};

    public static final int [] CRAZY_FROG_MELODY = {
            Notes.NOTES.get("A4"), Notes.NOTES.get("C5"), Notes.NOTES.get("A4"), Notes.NOTES.get("A4"), Notes.NOTES.get("D5"), Notes.NOTES.get("A4"), Notes.NOTES.get("G4"),
            Notes.NOTES.get("A4"), Notes.NOTES.get("E5"), Notes.NOTES.get("A4"), Notes.NOTES.get("A4"), Notes.NOTES.get("F5"), Notes.NOTES.get("E5"), Notes.NOTES.get("C5"),
            Notes.NOTES.get("A4"), Notes.NOTES.get("E5"), Notes.NOTES.get("A5"), Notes.NOTES.get("A4"), Notes.NOTES.get("G4"), Notes.NOTES.get("G4"), Notes.NOTES.get("E4"), Notes.NOTES.get("B4"),
            Notes.NOTES.get("A4"), 0,

            Notes.NOTES.get("A4"), Notes.NOTES.get("C5"), Notes.NOTES.get("A4"), Notes.NOTES.get("A4"), Notes.NOTES.get("D5"), Notes.NOTES.get("A4"), Notes.NOTES.get("G4"),
            Notes.NOTES.get("A4"), Notes.NOTES.get("E5"), Notes.NOTES.get("A4"), Notes.NOTES.get("A4"), Notes.NOTES.get("F5"), Notes.NOTES.get("E5"), Notes.NOTES.get("C5"),
            Notes.NOTES.get("A4"), Notes.NOTES.get("E5"), Notes.NOTES.get("A5"), Notes.NOTES.get("A4"), Notes.NOTES.get("G4"), Notes.NOTES.get("G4"), Notes.NOTES.get("E4"), Notes.NOTES.get("B4"),
            Notes.NOTES.get("A4"), 0,


            Notes.NOTES.get("A3"), Notes.NOTES.get("G3"), Notes.NOTES.get("E3"), Notes.NOTES.get("D3"),

            Notes.NOTES.get("A4"), Notes.NOTES.get("C5"), Notes.NOTES.get("A4"), Notes.NOTES.get("A4"), Notes.NOTES.get("D5"), Notes.NOTES.get("A4"), Notes.NOTES.get("G4"),
            Notes.NOTES.get("A4"), Notes.NOTES.get("E5"), Notes.NOTES.get("A4"), Notes.NOTES.get("A4"), Notes.NOTES.get("F5"), Notes.NOTES.get("E5"), Notes.NOTES.get("C5"),
            Notes.NOTES.get("A4"), Notes.NOTES.get("E5"), Notes.NOTES.get("A5"), Notes.NOTES.get("A4"), Notes.NOTES.get("G4"), Notes.NOTES.get("G4"), Notes.NOTES.get("E4"), Notes.NOTES.get("B4"),
            Notes.NOTES.get("A4")
    };

    public static final int [] CRAZY_FROG_TEMPO = {
            2,4,4,8,4,4,4,
            2,4,4,8,4,4,4,
            4,4,4,8,4,8,4,4,
            1,4,

            2,4,4,8,4,4,4,
            2,4,4,8,4,4,4,
            4,4,4,8,4,8,4,4,
            1,4,

            8,4,4,4,

            2,4,4,8,4,4,4,
            2,4,4,8,4,4,4,
            4,4,4,8,4,8,4,4,
            1};

    public static final int []  DECK_THE_HALLS_MELODY = {
            Notes.NOTES.get("G5"), Notes.NOTES.get("F5"), Notes.NOTES.get("E5"), Notes.NOTES.get("D5"),
            Notes.NOTES.get("C5"), Notes.NOTES.get("D5"), Notes.NOTES.get("E5"), Notes.NOTES.get("C5"),
            Notes.NOTES.get("D5"), Notes.NOTES.get("E5"), Notes.NOTES.get("F5"), Notes.NOTES.get("D5"), Notes.NOTES.get("E5"), Notes.NOTES.get("D5"),
            Notes.NOTES.get("C5"), Notes.NOTES.get("B4"), Notes.NOTES.get("C5"), 0,

            Notes.NOTES.get("G5"), Notes.NOTES.get("F5"), Notes.NOTES.get("E5"), Notes.NOTES.get("D5"),
            Notes.NOTES.get("C5"), Notes.NOTES.get("D5"), Notes.NOTES.get("E5"), Notes.NOTES.get("C5"),
            Notes.NOTES.get("D5"), Notes.NOTES.get("E5"), Notes.NOTES.get("F5"), Notes.NOTES.get("D5"), Notes.NOTES.get("E5"), Notes.NOTES.get("D5"),
            Notes.NOTES.get("C5"), Notes.NOTES.get("B4"), Notes.NOTES.get("C5"), 0,

            Notes.NOTES.get("D5"), Notes.NOTES.get("E5"), Notes.NOTES.get("F5"), Notes.NOTES.get("D5"),
            Notes.NOTES.get("E5"), Notes.NOTES.get("F5"), Notes.NOTES.get("G5"), Notes.NOTES.get("D5"),
            Notes.NOTES.get("E5"), Notes.NOTES.get("F5"), Notes.NOTES.get("G5"), Notes.NOTES.get("A5"), Notes.NOTES.get("B5"), Notes.NOTES.get("C6"),
            Notes.NOTES.get("B5"), Notes.NOTES.get("A5"), Notes.NOTES.get("G5"), 0,

            Notes.NOTES.get("G5"), Notes.NOTES.get("F5"), Notes.NOTES.get("E5"), Notes.NOTES.get("D5"),
            Notes.NOTES.get("C5"), Notes.NOTES.get("D5"), Notes.NOTES.get("E5"), Notes.NOTES.get("C5"),
            Notes.NOTES.get("D5"), Notes.NOTES.get("E5"), Notes.NOTES.get("F5"), Notes.NOTES.get("D5"), Notes.NOTES.get("E5"), Notes.NOTES.get("D5"),
            Notes.NOTES.get("C5"), Notes.NOTES.get("B4"), Notes.NOTES.get("C5"), 0
    };

    public static final int [] DECK_THE_HALLS_TEMPO = {
            2, 4, 2, 2,
            2, 2, 2, 2,
            4, 4, 4, 4, 2, 4,
            2, 2, 2, 2,

            2, 4, 2, 2,
            2, 2, 2, 2,
            4, 4, 4, 4, 2, 4,
            2, 2, 2, 2,

            2,4,2,2,
            2,4,2,2,
            4,4,2,4,4,2,
            2,2,2,2,

            2, 4, 2, 2,
            2, 2, 2, 2,
            4, 4, 4, 4, 2, 4,
            2, 2, 2, 2
};

    public static final int []   MANADERNA_MELODY = {
            Notes.NOTES.get("E4"), Notes.NOTES.get("E4"), Notes.NOTES.get("F4"), Notes.NOTES.get("G4"),
            Notes.NOTES.get("G4"), Notes.NOTES.get("F4"), Notes.NOTES.get("E4"), Notes.NOTES.get("D4"),
            Notes.NOTES.get("C4"), Notes.NOTES.get("C4"), Notes.NOTES.get("D4"), Notes.NOTES.get("E4"),
            Notes.NOTES.get("E4"), 0, Notes.NOTES.get("D4"), Notes.NOTES.get("D4"), 0,

            Notes.NOTES.get("E4"), Notes.NOTES.get("E4"), Notes.NOTES.get("F4"), Notes.NOTES.get("G4"),
            Notes.NOTES.get("G4"), Notes.NOTES.get("F4"), Notes.NOTES.get("E4"), Notes.NOTES.get("D4"),
            Notes.NOTES.get("C4"), Notes.NOTES.get("C4"), Notes.NOTES.get("D4"), Notes.NOTES.get("E4"),
            Notes.NOTES.get("D4"), 0, Notes.NOTES.get("C4"), Notes.NOTES.get("C4"), 0,

            Notes.NOTES.get("D4"), Notes.NOTES.get("D4"), Notes.NOTES.get("E4"), Notes.NOTES.get("C4"),
            Notes.NOTES.get("D4"), Notes.NOTES.get("E4"), Notes.NOTES.get("F4"), Notes.NOTES.get("E4"), Notes.NOTES.get("C4"),
            Notes.NOTES.get("D4"), Notes.NOTES.get("E4"), Notes.NOTES.get("F4"), Notes.NOTES.get("E4"), Notes.NOTES.get("D4"),
            Notes.NOTES.get("C4"), Notes.NOTES.get("D4"), Notes.NOTES.get("G3"), 0,

            Notes.NOTES.get("E4"), Notes.NOTES.get("E4"), Notes.NOTES.get("F4"), Notes.NOTES.get("G4"),
            Notes.NOTES.get("G4"), Notes.NOTES.get("F4"), Notes.NOTES.get("E4"), Notes.NOTES.get("D4"),
            Notes.NOTES.get("C4"), Notes.NOTES.get("C4"), Notes.NOTES.get("D4"), Notes.NOTES.get("E4"),
            Notes.NOTES.get("D4"), 0, Notes.NOTES.get("C4"), Notes.NOTES.get("C4")
    };

    public static final int []  MANADERNA_TEMPO = {
            2,2,2,2,
            2,2,2,2,
            2,2,2,2,
            2,4,4,2,4,

            2,2,2,2,
            2,2,2,2,
            2,2,2,2,
            2,4,4,2,4,

            2,2,2,2,
            2,4,4,2,2,
            2,4,4,2,2,
            2,2,1,4,

            2,2,2,2,
            2,2,2,2,
            2,2,2,2,
            2,4,4,2
};

    public static final int []   BONNAGARD_MELODY = {
            Notes.NOTES.get("C5"), Notes.NOTES.get("C5"), Notes.NOTES.get("C5"), Notes.NOTES.get("G4"),
            Notes.NOTES.get("A4"), Notes.NOTES.get("A4"), Notes.NOTES.get("G4"),
            Notes.NOTES.get("E5"), Notes.NOTES.get("E5"), Notes.NOTES.get("D5"), Notes.NOTES.get("D5"),
            Notes.NOTES.get("C5"), 0, Notes.NOTES.get("G4"),

            Notes.NOTES.get("C5"), Notes.NOTES.get("C5"), Notes.NOTES.get("C5"), Notes.NOTES.get("G4"),
            Notes.NOTES.get("A4"), Notes.NOTES.get("A4"), Notes.NOTES.get("G4"),
            Notes.NOTES.get("E5"), Notes.NOTES.get("E5"), Notes.NOTES.get("D5"), Notes.NOTES.get("D5"),
            Notes.NOTES.get("C5"), 0, Notes.NOTES.get("G4"), Notes.NOTES.get("G4"),

            Notes.NOTES.get("C5"), Notes.NOTES.get("C5"), Notes.NOTES.get("C5"), Notes.NOTES.get("G4"), Notes.NOTES.get("G4"),
            Notes.NOTES.get("C5"), Notes.NOTES.get("C5"), Notes.NOTES.get("G4"),
            Notes.NOTES.get("C5"), Notes.NOTES.get("C5"), Notes.NOTES.get("C5"), Notes.NOTES.get("C5"), Notes.NOTES.get("C5"), Notes.NOTES.get("C5"),
            Notes.NOTES.get("C5"), Notes.NOTES.get("C5"), Notes.NOTES.get("C5"), Notes.NOTES.get("C5"), Notes.NOTES.get("C5"), Notes.NOTES.get("C5"), 0,

            Notes.NOTES.get("C5"), Notes.NOTES.get("C5"), Notes.NOTES.get("C5"), Notes.NOTES.get("G4"),
            Notes.NOTES.get("A4"), Notes.NOTES.get("A4"), Notes.NOTES.get("G4"),
            Notes.NOTES.get("E5"), Notes.NOTES.get("E5"), Notes.NOTES.get("D5"), Notes.NOTES.get("D5"),
            Notes.NOTES.get("C5"), 0
    };

    public static final int []  BONNAGARD_TEMPO = {
            2, 2, 2, 2,
            2, 2, 1,
            2, 2, 2, 2,
            1, 2, 2,

            2, 2, 2, 2,
            2, 2, 1,
            2, 2, 2, 2,
            1, 2, 4, 4,

            2, 2, 2, 4, 4,
            2, 2, 1,
            4, 4, 2, 4, 4, 2,
            4, 4, 4, 4, 2, 2, 4,

            2, 2, 2, 2,
            2, 2, 1,
            2, 2, 2, 2,
            1, 1
    };

    public static final int []  FINAL_COUNTDOWN_MELODY = {
            Notes.NOTES.get("A3"), Notes.NOTES.get("E5"), Notes.NOTES.get("D5"), Notes.NOTES.get("E5"), Notes.NOTES.get("A4"),
            Notes.NOTES.get("F3"), Notes.NOTES.get("F5"), Notes.NOTES.get("E5"), Notes.NOTES.get("F5"), Notes.NOTES.get("E5"), Notes.NOTES.get("D5"),
            Notes.NOTES.get("D3"), Notes.NOTES.get("F5"), Notes.NOTES.get("E5"), Notes.NOTES.get("F5"), Notes.NOTES.get("A4"),
            Notes.NOTES.get("G3"), 0, Notes.NOTES.get("D5"), Notes.NOTES.get("C5"), Notes.NOTES.get("D5"), Notes.NOTES.get("C5"), Notes.NOTES.get("B4"), Notes.NOTES.get("D5"),
            Notes.NOTES.get("C5"), Notes.NOTES.get("A3"), Notes.NOTES.get("E5"), Notes.NOTES.get("D5"), Notes.NOTES.get("E5"), Notes.NOTES.get("A4"),
            Notes.NOTES.get("F3"), Notes.NOTES.get("F5"), Notes.NOTES.get("E5"), Notes.NOTES.get("F5"), Notes.NOTES.get("E5"), Notes.NOTES.get("D5"),
            Notes.NOTES.get("D3"), Notes.NOTES.get("F5"), Notes.NOTES.get("E5"), Notes.NOTES.get("F5"), Notes.NOTES.get("A4"),
            Notes.NOTES.get("G3"), 0, Notes.NOTES.get("D5"), Notes.NOTES.get("C5"), Notes.NOTES.get("D5"), Notes.NOTES.get("C5"), Notes.NOTES.get("B4"), Notes.NOTES.get("D5"),
            Notes.NOTES.get("C5"), Notes.NOTES.get("B4"), Notes.NOTES.get("C5"), Notes.NOTES.get("D5"), Notes.NOTES.get("C5"), Notes.NOTES.get("D5"),
            Notes.NOTES.get("E5"), Notes.NOTES.get("D5"), Notes.NOTES.get("C5"), Notes.NOTES.get("B4"), Notes.NOTES.get("A4"), Notes.NOTES.get("F5"),
            Notes.NOTES.get("E5"), Notes.NOTES.get("E5"), Notes.NOTES.get("F5"), Notes.NOTES.get("E5"), Notes.NOTES.get("D5"),
            Notes.NOTES.get("E5")
    };




    public static final int [] FINAL_COUNTDOWN_TEMPO = {1,16,16,4,4,
            1,16,16,8,8,4,
            1,16,16,4,4,
            2,4,16,16,8,8,8,8,
            4,4,16,16,4,4,
            1,16,16,8,8,4,
            1,16,16,4,4,
            2,4,16,16,8,8,8,8,
            4,16,16,4,16,16,
            8,8,8,8,4,4,
            2,8,4,16,16,1};

}
