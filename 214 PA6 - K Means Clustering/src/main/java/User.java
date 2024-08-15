import java.util.*;

public class User {

    public static Double[] getColumnVal(Double[][] matrix, int col){
        Double[] arrayColumn = new Double[matrix.length];
        for (int i = 0; i < matrix.length; ++i){
            arrayColumn[i] = matrix[i][col];
        }
        return arrayColumn;
    }

    public static boolean matrixSizeCheck(Double[][] matrix){
        int size = matrix[0].length;
        for (Double[] row: matrix){
            if (row.length != size){return true;}
        }
        return false;
    }

    public static Double[][] filterUncooperativeUser(Double[][] matrix){
        Double[][] matrixDouble = new Double[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; ++i){
            for (int j = 0; j < matrix[0].length; ++j){
                matrixDouble[j][i] = matrix[i][j];
            }
        }
        List<Double[]> filteredUsers = new ArrayList<>();
        for (int k = 0; k < matrixDouble[0].length; ++k){
            Double[] arrColumn = getColumnVal(matrixDouble, k);
            Double[] nonZeroValuesNoNAN = filterNonZeroValues(arrColumn);
            long nonZeroValueCount = countDistinctValues(nonZeroValuesNoNAN);
            if (!(nonZeroValueCount <= 1)){filteredUsers.add(arrColumn);}
        }
        return filteredUsers.toArray(new Double[0][0]);
    }

    private static Double[] filterNonZeroValues(Double[] arrColumn){
        List<Double> nonZeroValues = new ArrayList<>();
        for (Double db : arrColumn){
            if (!Double.isNaN(db) && db != 0){
                nonZeroValues.add(db);
            }
        }
        return nonZeroValues.toArray(new Double[0]);
    }

    private static long countDistinctValues(Double[] arrColumn){
        Set<Double> distinctValues = new HashSet<>(Arrays.asList(arrColumn));
        return distinctValues.size();
    }

    public static Double[][] normalizeUser(Double[][] matrix){
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        Double[][] normalizedMatrix = new Double[numRows][numCols];
        for (int i = 0; i < numCols; ++i){
            Double[] arrColumn = getColumnVal(matrix, i);
            Double mean = Calculation.averageDouble(arrColumn);
            Double sdv = Calculation.sdvDouble(arrColumn);
            for (int j = 0; j < numRows; ++j){
                normalizedMatrix[j][i] = (matrix[j][i] - mean) / sdv;
            }
        }
        return normalizedMatrix;
    }

    public static Double[][] confirmMatrixEntries(Double[][] matrix, Double[][] normalized, Double[][] similarityMatrix){
        Double[][] matrixDouble = new Double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; ++i){
            System.arraycopy(matrix[i], 0, matrixDouble[i], 0, matrix[i].length);
        }
        for (int row = 0; row < matrixDouble.length; ++row){
            for (int column = 0; column < matrixDouble[row].length; column++){
                if (Double.isNaN(matrixDouble[row][column])){
                    int idx = Calculation.userSimilarityCheck(similarityMatrix, matrix, row, column);
                    if (idx != -1){
                        Double checkDouble = normalized[idx][column];
                        if (!Double.isNaN(checkDouble)){
                            matrixDouble[row][column] = (double) Calculation.arrayDenormalization(getColumnVal(matrix, column), checkDouble);
                        }
                    }
                }
            }
        }
        return matrixDouble;
    }
}