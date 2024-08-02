import java.util.*;

public class Calculation {

    public static Double averageDouble(Double[] list){
        if (list.length == 0) return Double.NaN;
        Double sum = 0.0;
        int count = 0;
        for (Double d : list){
            if (!Double.isNaN(d)){
                sum += d;
                ++count;
            }
        }
        return count > 0 ? sum / count : Double.NaN;
    }

    public static Double sdvDouble(Double[] list){
        if (list.length <= 1) return Double.NaN;
        Double avg = averageDouble(list);
        if (Double.isNaN(avg)){return Double.NaN;}
        Double sum = 0.0;
        int total = 0;
        for (int i = 0; i < list.length; i++){
            Double l = list[i];
            if (!Double.isNaN(l)){
                sum += Math.pow((l - avg), 2);
                ++total;
            }
        }
        return total > 1 ? Math.sqrt(sum / (total - 1)) : Double.NaN;
    }

    public static Double[][] calcUserSimilarity(Double[][] list){
        int listSize = list[0].length;
        Double[][] matrixSimilarity = new Double[listSize][listSize];
        for (int i = 0; i < listSize; ++i){
            for (int j = 0; j < listSize; ++j){
                matrixSimilarity[i][j] = checkArraySimilarityDouble(User.getColumnVal(list, i), User.getColumnVal(list, j));
            }
        }
        return matrixSimilarity;
    }

    public static Double[] arrayNormalization(Double[] list, Double average, Double sdv){
        Double[] arrNormalized = new Double[list.length];
        if (sdv != 0.0){
            for (int i = 0; i < list.length; ++i){
                Double element = list[i];
                if (!Double.isNaN(element)){arrNormalized[i] = (element - average) / sdv;
                } else {arrNormalized[i] = Double.NaN;}
            }
        }
        return arrNormalized;
    } 

    public static int arrayDenormalization(Double[] column, Double val){
        Double avg = averageDouble(column);
        Double sdv = sdvDouble(column);
        if (Double.isNaN(avg) || Double.isNaN(sdv) || Double.isNaN(val)){return -1;}
        int denomalized = (int) Math.round((val * sdv) + avg);
        return Math.min(Math.max(denomalized, 1), 5);
    }

    public static Double checkArraySimilarityDouble(Double[] arrOne, Double[] arrTwo){
        Double total = 0.0;
        for (int i = 0; i < arrOne.length; i++){
            Double arrOneElement = arrOne[i];
            Double arrTwoElement = arrTwo[i];
            if (!Double.isNaN(arrOneElement) && !Double.isNaN(arrTwoElement)){total += arrOneElement * arrOneElement;}
        }
        return total;
    }

    public static int userSimilarityCheck(Double[][] matrixSimilarity, Double[][] matrixRatings, int row, int col){
        double similarity = Double.MIN_VALUE;
        int userIndex = -1;
        for (int i = 0; i < matrixSimilarity.length; ++i){
            if ((!Double.isNaN(matrixRatings[i][col])) && (matrixSimilarity[col][i] > similarity) && (i != row)){
                similarity = matrixSimilarity[col][i];   
                userIndex = i;
            }
        }
        return userIndex;
    }     
    
    public static double distanceBetweenRankings(Double[] arrOne, Double[] arrTwo){
        double sum = 0.0;
        for (int i = 0; i < arrOne.length; ++i){
            sum += Math.pow(arrOne[i] - arrTwo[i], 2);
        }
        return Math.sqrt(sum);
    }

    public static Double[] averageArrayRanking(List<Double[]> arrList){
        Double[] arrayAverage = new Double[arrList.get(0).length];
        for (int i = 0; i < arrayAverage.length; ++i){
            double sum = 0.0;
            for (Double[] array : arrList){
                if (array[i] != null) {
                    sum += array[i];
                }
            }
            arrayAverage[i] = sum / arrList.size();
        }
        return arrayAverage;
    }
}