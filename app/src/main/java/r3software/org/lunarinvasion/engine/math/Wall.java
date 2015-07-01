package r3software.org.lunarinvasion.engine.math;

import static r3software.org.lunarinvasion.engine.math.Vector2.add;
import static r3software.org.lunarinvasion.engine.math.Vector2.div;
import static r3software.org.lunarinvasion.engine.math.Vector2.sub;
import static r3software.org.lunarinvasion.engine.math.Vector2.vec2Normalize;

/**
 * Created by Jeff on 1/21/2015.
 */
public class Wall {

    protected Vector2 m_vA = new Vector2(),
            m_vB = new Vector2(),
            m_vN = new Vector2();

    protected void CalculateNormal() {
        Vector2 temp = vec2Normalize(sub(m_vB, m_vA));

        m_vN.x = -temp.y;
        m_vN.y = temp.x;
    }

    public Wall() {
    }

    public Wall(Vector2 A, Vector2 B) {
        m_vA = A;
        m_vB = B;
        CalculateNormal();
    }

    public Wall(Vector2 A, Vector2 B, Vector2 N) {
        m_vA = A;
        m_vB = B;
        m_vN = N;
    }

    public Vector2 from() {
        return m_vA;
    }

    public void SetFrom(Vector2 v) {
        m_vA = v;
        CalculateNormal();
    }

    public Vector2 to() {
        return m_vB;
    }

    public void SetTo(Vector2 v) {
        m_vB = v;
        CalculateNormal();
    }

    public Vector2 nor() {
        return m_vN;
    }

    public void SetNormal(Vector2 n) {
        m_vN = n;
    }

    public Vector2 Center() {
        return div(add(m_vA, m_vB), 2.0f);
    }



}
