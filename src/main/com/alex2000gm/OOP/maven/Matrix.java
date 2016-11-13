import java.util.ArrayList;

public class Matrix {
    private int horizontalSize;
    private int verticalSize;
    private Vector[] matrix;

    public Matrix(int horizontalSize, int verticalSize) {
        this.horizontalSize = horizontalSize;
        this.verticalSize = verticalSize;
        this.matrix = new Vector[verticalSize];
        for (int i = 0; i < verticalSize; i++) {
            matrix[i] = new Vector(horizontalSize);
        }
    }

    public Matrix(Vector... vectors) {
        this.verticalSize = vectors.length;
        this.matrix = new Vector[verticalSize];
        int maxSize = 0;
        for (int i = 0; i < verticalSize; i++) {
            if (vectors[i].getSize() > maxSize) {
                maxSize = vectors[i].getSize();
            }
        }
        this.horizontalSize = maxSize;
        for (int i = 0; i < verticalSize; i++) {
            if (vectors[i].getSize() < maxSize) {
                double[] valueForConstructor = vectors[i].getVector();
                this.matrix[i] = new Vector(maxSize, valueForConstructor);
            } else {
                this.matrix[i] = new Vector(vectors[i]);
            }
        }
    }

    public Matrix(double[][] arrays) {
        int maxArrayLength = 0;
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > maxArrayLength) {
                maxArrayLength = arrays[i].length;
            }
            this.horizontalSize = arrays.length;
            this.verticalSize = maxArrayLength;
        }
        for (int i = 0; i < arrays.length; i++) {
            this.matrix[i] = new Vector(maxArrayLength, arrays[i]);
        }
    }

    public Matrix(Matrix matrixForCopy) {
        Matrix copyOfMatrix = new Matrix(matrixForCopy.getHorizontalSize(), matrixForCopy.getVerticalSize());
        for (int i = 0; i < horizontalSize; i++) {
            copyOfMatrix.getMatrix()[i] = new Vector(matrixForCopy.getMatrix()[i]);
        }
    }

    public int getHorizontalSize() {
        return horizontalSize;
    }

    public int getVerticalSize() {
        return verticalSize;
    }

    public Vector[] getMatrix() {
        return matrix;
    }

    public Vector getVectorByIndex(int index) {
        return this.getMatrix()[index];
    }

    public Vector getColumnVector(int indexForSearch) {
        Vector column = new Vector(this.getVerticalSize());
        for (int i = 0; i < this.getVerticalSize(); i++) {
            column.setNumByIndex(i, getVectorByIndex(i).getNumByIndex(indexForSearch));
        }
        return column;
    }

    public Matrix getTransposedMatrix(Matrix matrix) {
        Vector[] columnVectors = new Vector[matrix.getHorizontalSize()];
        for (int i = 0; i < matrix.getHorizontalSize(); i++) {
            columnVectors[i] = matrix.getColumnVector(i);
        }
        return new Matrix(columnVectors);
    }

    public Matrix getMultiplied(double scalar) {
        Vector[] multipliedVectors = new Vector[this.getVerticalSize()];
        for (int i = 0; i < this.getVerticalSize(); i++) {
            multipliedVectors[i] = this.getVectorByIndex(i).getMultiplied(scalar);
        }
        return new Matrix(multipliedVectors);
    }

    public Matrix getMultiplied(Vector vector) {
        Matrix multiplication = new Matrix(1, this.getVerticalSize());
        for (int i = 0; i < this.getVerticalSize(); i++) {
            double[] resultOfMultiply = {Vector.getMultiplied(this.getVectorByIndex(i), vector)};
            multiplication.setVectorByIndex(i, new Vector(resultOfMultiply));
        }
        return multiplication;
    }

    public Matrix getSumOfMatrixes(Matrix matrix) {
        Matrix sumOfMatrixes = new Matrix(Math.max(this.getHorizontalSize(), matrix.getHorizontalSize()), Math.max(this.getVerticalSize(), matrix.getVerticalSize()));
        for (int i = 0; i < matrix.getVerticalSize(); i++) {
            sumOfMatrixes.setVectorByIndex(i, this.getVectorByIndex(i).sumOfVectors(matrix.getVectorByIndex(i)));
        }
        return sumOfMatrixes;
    }

    public Matrix getDifferenceOfMatrixes(Matrix matrix) {
        Matrix differenceOfMatrixes = new Matrix(Math.max(this.getHorizontalSize(), matrix.getHorizontalSize()), Math.max(this.getVerticalSize(), matrix.getVerticalSize()));
        for (int i = 0; i < matrix.getVerticalSize(); i++) {
            differenceOfMatrixes.setVectorByIndex(i, this.getVectorByIndex(i).differenceOfVectors(matrix.getVectorByIndex(i)));
        }
        return differenceOfMatrixes;
    }

 /*   public double getDeterminant() {
        double determinant = 0;
        int signOfDeterminant = 1;
        // Проход по главной диагонали в поисках нулей и замена столбцов и строк если нужно
        for (int i = 0; i < this.getVerticalSize(); i++) {
            if (this.getVectorByIndex(i).getNumByIndex(i) == 0) {
                boolean stillZero = true;
                for (int j = i; j < this.getVectorByIndex(i).getSize(); j++) {
                    if (this.getVectorByIndex(i).getNumByIndex(j) != 0) {
                        Vector zeroValue = this.getColumnVector(i);
                        Vector noZeroValue = this.getColumnVector(j);
                        setColumnVector(i, noZeroValue);
                        setColumnVector(j, zeroValue);
                        signOfDeterminant = signOfDeterminant * (-1);
                        stillZero = false;
                    }
                }
                if (stillZero) {
                    for (int j = i; j < this.getVerticalSize(); j++) {
                        if (this.getVectorByIndex(j).getNumByIndex(i) != 0) {
                            Vector zeroValue = new Vector(this.getVectorByIndex(i));
                            Vector noZeroValue = new Vector(this.getVectorByIndex(j));
                            setVectorByIndex(i, noZeroValue);
                            setVectorByIndex(j, zeroValue);
                            signOfDeterminant = signOfDeterminant * (-1);
                            stillZero = false;
                        }
                    }
                }
            }
        }


        for (int i = 0; i < this.getVerticalSize(); i++) {
            double[] multipliers = new double[];
            if (this.getVectorByIndex(i).getNumByIndex(i) != 0) {
                getMultiplier(i, )
            }
        }

        return determinant;
    }   */

 /* 1 список для номера строки и второй для номера столбца; смотрим что в списке номер 1 нет следующего индекса и делаем тоже для столбца;
     как дошли до конца, так восстанавливаем список и переходим к следующему члену в строке
    */

    public double getDeterminant() {
        double determinant = 0;
        int numOfCombinations = 1;
        for (int i = 1; i <= this.getHorizontalSize(); i++) {
            numOfCombinations = numOfCombinations * i;
        }
        int sameMultipliers = numOfCombinations / this.getHorizontalSize();

        ArrayList<Vector> matrixIndexes = new ArrayList<>();
        ArrayList<Matrix> combinations = new ArrayList<>();
        Matrix temporary = new Matrix(3, this.getVerticalSize());

        for (int k = 0; k <= numOfCombinations; k++) {

            for (int i = 0; i < this.getHorizontalSize(); i++) {
                for (int j = 0; j < this.getVerticalSize(); j++) {



                    temporary.setVectorByIndex(i, new Vector(i, j, this.getVectorByIndex(i).getNumByIndex(j)));
                    //

                    // this.getVectorByIndex(i).getNumByIndex(j);
                }
            }combinations.add(temporary);
        }


        double multiplier = 0;
        double sum = 0;
        while (!matrixIndexes.isEmpty()) {
            matrixIndexes.iterator();
        }

        for (int i = 0, j = 0; i < this.getHorizontalSize(); i++, j++) {
            this.getVectorByIndex(i).getNumByIndex(j);

            for (int k = 0; k < sameMultipliers; k++) {
                matrixIndexes.add(new Vector(i, j));
            }
        }
        return determinant;
    }


    public static Matrix sumOfMatrixes(Matrix matrix1, Matrix matrix2) {
        Matrix sumOfMatrixes = new Matrix(Math.max(matrix1.getHorizontalSize(), matrix2.getHorizontalSize()), Math.max(matrix1.getVerticalSize(), matrix2.getVerticalSize()));
        for (int i = 0; i < sumOfMatrixes.getVerticalSize(); i++) {
            if (i < Math.min(matrix1.getVerticalSize(), matrix2.getVerticalSize())) {
                sumOfMatrixes.setVectorByIndex(i, matrix1.getVectorByIndex(i).sumOfVectors(matrix2.getVectorByIndex(i)));
            } else {
                if (matrix1.getVerticalSize() > matrix2.getVerticalSize()) {
                    sumOfMatrixes.setVectorByIndex(i, matrix1.getVectorByIndex(i));
                } else {
                    sumOfMatrixes.setVectorByIndex(i, matrix2.getVectorByIndex(i));
                }
            }
        }
        return sumOfMatrixes;
    }

    public static Matrix differenceOfMatrixes(Matrix matrix1, Matrix matrix2) {
        Matrix difference = new Matrix(Math.max(matrix1.getHorizontalSize(), matrix2.getHorizontalSize()), Math.max(matrix1.getVerticalSize(), matrix2.getVerticalSize()));
        for (int i = 0; i < difference.getVerticalSize(); i++) {
            if (i < Math.min(matrix1.getVerticalSize(), matrix2.getVerticalSize())) {
                difference.setVectorByIndex(i, matrix1.getVectorByIndex(i).differenceOfVectors(matrix2.getVectorByIndex(i)));
            } else {
                if (matrix1.getVerticalSize() > matrix2.getVerticalSize()) {
                    difference.setVectorByIndex(i, matrix1.getVectorByIndex(i));
                } else {
                    difference.setVectorByIndex(i, matrix2.getVectorByIndex(i).getInverse());
                }
            }
        }
        return difference;
    }

    public static Matrix getMultiplied(Matrix matrix1, Matrix matrix2) {
        Matrix multiplication = new Matrix(matrix2.getHorizontalSize(), matrix1.getVerticalSize());
        for (int i = 0; i < multiplication.getVerticalSize(); i++) {
            Vector rowOfResults = new Vector(multiplication.getHorizontalSize());
            for (int j = 0; j < multiplication.getHorizontalSize(); j++) {
                double result = Vector.getMultiplied(matrix1.getVectorByIndex(i), matrix2.getColumnVector(j));//double значение произведения
                rowOfResults.setNumByIndex(j, result);
            }
            multiplication.setVectorByIndex(i, rowOfResults);
        }
        return multiplication;
    }

    public void setVectorByIndex(int index, Vector vector) {
        this.getMatrix()[index] = new Vector(vector);
    }

    public void setColumnVector(int indexForSearch, Vector vector) {
        for (int i = 0; i < this.getVerticalSize(); i++) {
            this.getVectorByIndex(i).setNumByIndex(indexForSearch, vector.getNumByIndex(i));
        }
    }

    @Override
    public String toString() {
        StringBuilder printMatrix = new StringBuilder();
        printMatrix.append("{");
        int index = 0;
        while (index < this.getVerticalSize()) {
            printMatrix.append("{");
            printMatrix.append(this.getVectorByIndex(index).toString());
            if (index < this.getVerticalSize() - 1) {
                printMatrix.append("}, ");
            } else {
                printMatrix.append("}");
            }
            index++;
        }
        printMatrix.append("}");
        return printMatrix.toString();
    }

    private static double getMultiplier(int index1, int index2, Vector vector) {
        return vector.getNumByIndex(index2) / vector.getNumByIndex(index1);
    }
}