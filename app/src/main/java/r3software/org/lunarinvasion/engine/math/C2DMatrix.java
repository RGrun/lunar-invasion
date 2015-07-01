package r3software.org.lunarinvasion.engine.math;

import java.util.List;
import java.util.ListIterator;

public class C2DMatrix {

    private class Matrix {

        float _11, _12, _13;
        float _21, _22, _23;
        float _31, _32, _33;

        Matrix() {
            _11 = 0.0f;
            _12 = 0.0f;
            _13 = 0.0f;
            _21 = 0.0f;
            _22 = 0.0f;
            _23 = 0.0f;
            _31 = 0.0f;
            _32 = 0.0f;
            _33 = 0.0f;
        }
    }
    private Matrix m_Matrix = new Matrix();

    public C2DMatrix() {
        //initialize the matrix to an identity matrix
        identity();
    }

    //accessors to the matrix elements
    public void _11(float val) {
        m_Matrix._11 = val;
    }

    public void _12(float val) {
        m_Matrix._12 = val;
    }

    public void _13(float val) {
        m_Matrix._13 = val;
    }

    public void _21(float val) {
        m_Matrix._21 = val;
    }

    public void _22(float val) {
        m_Matrix._22 = val;
    }

    public void _23(float val) {
        m_Matrix._23 = val;
    }

    public void _31(float val) {
        m_Matrix._31 = val;
    }

    public void _32(float val) {
        m_Matrix._32 = val;
    }

    public void _33(float val) {
        m_Matrix._33 = val;
    }

    //multiply two matrices together
    private void matrixMultiply(Matrix mIn) {
        Matrix mat_temp = new Matrix();

        //first row
        mat_temp._11 = (m_Matrix._11 * mIn._11) + (m_Matrix._12 * mIn._21) + (m_Matrix._13 * mIn._31);
        mat_temp._12 = (m_Matrix._11 * mIn._12) + (m_Matrix._12 * mIn._22) + (m_Matrix._13 * mIn._32);
        mat_temp._13 = (m_Matrix._11 * mIn._13) + (m_Matrix._12 * mIn._23) + (m_Matrix._13 * mIn._33);

        //second
        mat_temp._21 = (m_Matrix._21 * mIn._11) + (m_Matrix._22 * mIn._21) + (m_Matrix._23 * mIn._31);
        mat_temp._22 = (m_Matrix._21 * mIn._12) + (m_Matrix._22 * mIn._22) + (m_Matrix._23 * mIn._32);
        mat_temp._23 = (m_Matrix._21 * mIn._13) + (m_Matrix._22 * mIn._23) + (m_Matrix._23 * mIn._33);

        //third
        mat_temp._31 = (m_Matrix._31 * mIn._11) + (m_Matrix._32 * mIn._21) + (m_Matrix._33 * mIn._31);
        mat_temp._32 = (m_Matrix._31 * mIn._12) + (m_Matrix._32 * mIn._22) + (m_Matrix._33 * mIn._32);
        mat_temp._33 = (m_Matrix._31 * mIn._13) + (m_Matrix._32 * mIn._23) + (m_Matrix._33 * mIn._33);

        m_Matrix = mat_temp;
    }

    //applies a 2D transformation matrix to a list of Vector2s
    public void transformVector2s(List<Vector2> vPoint) {
        ListIterator<Vector2> it = vPoint.listIterator();
        while (it.hasNext()) {
            Vector2 i = it.next();
            float tempX = (m_Matrix._11 * i.x) + (m_Matrix._21 * i.y) + (m_Matrix._31);
            float tempY = (m_Matrix._12 * i.x) + (m_Matrix._22 * i.y) + (m_Matrix._32);
            i.x = tempX;
            i.y = tempY;
        }
    }

    //applies a 2D transformation matrix to a single Vector2
    public void transformVector2s(Vector2 vPoint) {

        float tempX = (m_Matrix._11 * vPoint.x) + (m_Matrix._21 * vPoint.y) + (m_Matrix._31);
        float tempY = (m_Matrix._12 * vPoint.x) + (m_Matrix._22 * vPoint.y) + (m_Matrix._32);

        vPoint.x = tempX;
        vPoint.y = tempY;
    }

    //create an identity matrix
    public void identity() {
        m_Matrix._11 = 1;
        m_Matrix._12 = 0;
        m_Matrix._13 = 0;
        m_Matrix._21 = 0;
        m_Matrix._22 = 1;
        m_Matrix._23 = 0;
        m_Matrix._31 = 0;
        m_Matrix._32 = 0;
        m_Matrix._33 = 1;

    }

    //create a transformation matrix
    public void translate(float x, float y) {
        Matrix mat = new Matrix();

        mat._11 = 1; mat._12 = 0; mat._13 = 0;

        mat._21 = 0; mat._22 = 1; mat._23 = 0;

        mat._31 = x; mat._32 = y; mat._33 = 1;

        //and multiply
        matrixMultiply(mat);
    }

    //create a scale matrix
    public void scale(float xScale, float yScale) {
        Matrix mat = new Matrix();

        mat._11 = xScale; mat._12 = 0; mat._13 = 0;

        mat._21 = 0; mat._22 = yScale; mat._23 = 0;

        mat._31 = 0; mat._32 = 0; mat._33 = 1;

        //and multiply
        matrixMultiply(mat);
    }

    //create a rotation matrix
    public void rotate(float rot) {
        Matrix mat = new Matrix();

        float Sin = (float) Math.sin(rot);
        float Cos = (float) Math.cos(rot);

        mat._11 = Cos; mat._12 = Sin; mat._13 = 0;
        mat._21 = -Sin; mat._22 = Cos; mat._23 = 0;
        mat._31 = 0; mat._32 = 0; mat._33 = 1;

        //and multiply
        matrixMultiply(mat);
    }

    //create a rotation matrix from a 2D vector
    public void rotate(Vector2 fwd, Vector2 side) {
        Matrix mat = new Matrix();

        mat._11 = fwd.x; mat._12 = fwd.y;  mat._13 = 0;
        mat._21 = side.x; mat._22 = side.y; mat._23 = 0;
        mat._31 = 0;mat._32 = 0;mat._33 = 1;

        //and multiply
        matrixMultiply(mat);
    }
}
