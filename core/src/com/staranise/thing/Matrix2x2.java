package com.staranise.thing;

/**
 * Created by 현성 on 2016-07-19.
 */
public class Matrix2x2 {
    public float[][] matData = new float[2][2];

    public Matrix2x2(){
    }

    public static Vec2 Mat3x3Rotation(Vec2 target, float angle){
        Matrix2x2 mat = new Matrix2x2();
        mat.matData[0][0] = (float)Math.cos(angle);
        mat.matData[0][1] = -(float)Math.sin(angle);
        mat.matData[1][0] = (float)Math.sin(angle);
        mat.matData[1][1] = (float)Math.cos(angle);

        float[] vec2 = {target.x, target.y};
        float[] ret = new float[2];

        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                ret[i] += mat.matData[i][j] * vec2[j];
            }
        }

        target.x = ret[0];
        target.y = ret[1];
        return target;
    }
}
