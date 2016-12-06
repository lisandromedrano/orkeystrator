package main.groovy

import jm.constants.Scales

import static jm.constants.Pitches.*

class AlphabeticMapper implements main.groovy.KeyMapper {
    int rootPitch = C4
    int octaves = 2
    int [] scale = Scales.PENTATONIC_SCALE;


    @Override
    public int getNote(char c) {
        //A = 65
        //z = 122
        //middle C 60
        //C scale 60 62 64 65 67 69 71
        //         a  b  c  d  e f   g
        int note = Character.toLowerCase(c)
        getNoteScale(note % 10)
//        switch (note % 14){
//            case 0:return 60
//            case 1:return 62
//            case 2:return 64
//            case 3:return 65
//            case 4:return 67
//            case 5:return 69
//            case 6:return 71
//            case 7:return 72
//            case 8:return 74
//            case 9:return 76
//            case 10:return 77
//            case 11:return 79
//            case 12:return 81
//            case 13:return 83
////            case 14:return 84
////            case 15:return 86
////            case 16:return 88
////            case 17:return 89
////            case 18:return 91
////            case 19:return 93
////            case 20:return 95
//        }
//        return 60 + note % 12
    }

    int getNoteScale(int note){
        int octave = 0
            octave = (note / scale.size()) - 1
        int index = note % scale.size() - octave
        return octave + rootPitch + scale[index];
//         switch(note){
//             case 0: return octave + rootPitch
//             case 1: return octave + rootPitch + 2
//             case 2: return octave + rootPitch + 4
//             case 3: return octave + rootPitch + 5
//             case 4: return octave + rootPitch + 7
//             case 5: return octave + rootPitch + 9
//             case 6: return octave + rootPitch + 11
//             default: return octave + rootPitch
//         }
    }

    public void setStartNote(int note){
        rootPitch = note;
    }

    @Override
    public void setScale(int[] scale){
        this.scale = scale
    }

}
