using UnityEngine;
using System.Collections;
using System.Collections.Generic;
 
public class MouseMove2D : MonoBehaviour {
 
    private Vector3 mousePosition;
    private Vector3 fingerPosition;
    public float jointDistance;
    public Texture2D image;
    Texture2D texture;
    Rect texturePos;
    bool done = false;
    bool dragMode = false;
    List<Vector3> joints;
    Vector3[] lines;
    Color maskColor;
    public Material whitemat;
    int numLines = 1;
 
    // Use this for initialization
    void Start () {
        joints = new List<Vector3>();
        texturePos = new Rect(0,0,0,0);
        maskColor = new Color(150.0f, 50.0f, 0.0f); 
        texture = new Texture2D(10,10);
    }
   
    // Update is called once per frame
    void Update () {
        if (!done) {
            if (Input.GetMouseButton(0)) {
                mousePosition = Input.mousePosition;
                mousePosition = Camera.main.ScreenToWorldPoint(mousePosition);
                transform.position = mousePosition;
                //fingerPosition = Input.GetTouch(0).position;
                //fingerPosition = Camera.main.ScreenToWorldPoint(fingerPosition);
                //transform.position = mousePosition;
                
                if (joints.Count == 0) {
                    joints.Add(mousePosition);
                    //gameObject.GetComponent<LineRenderer>().numPositions = joints.Count;
                    Vector3 t = mousePosition;
                    t.z = 6.0f;
                    GetComponent<LineRenderer>().SetPosition(0, t);
                }
                else if (Vector3.Distance(mousePosition, joints[joints.Count-1]) > jointDistance) {
                    joints.Add(mousePosition);
                    //gameObject.GetComponent<LineRenderer>().numPositions = joints.Count;
                    Vector3 t = mousePosition;
                    t.z = 6.0f;
                    GetComponent<LineRenderer>().SetPosition(numLines, t);
                    numLines++;
                }
                if (joints.Count > 3) {
                    if (Vector3.Distance(mousePosition, joints[0]) < jointDistance) {
                        done = true;
                        Debug.Log("Closed lasso");
                        
                        Vector3 r = joints[0];
                        r.z = 6.0f;
                        GetComponent<LineRenderer>().SetPosition(numLines, r);
                        LassoClosed();
                    }
                }
            }
            if (joints.Count < 2) return;
            for (int i = 0; i < joints.Count-1; i++) {
                Debug.DrawLine (joints[i], joints[i+1], Color.green);
            }
            Debug.DrawLine (mousePosition, joints[joints.Count-1], Color.yellow);
            Vector3 m = mousePosition;
            m.z = 6.0f;
            //if (!done) GetComponent<LineRenderer>().SetPosition(numLines, m);
        }
        
        else { //lasso closed
            if (!dragMode) {
                if (Input.GetKeyDown(KeyCode.Y)) {
                    GenerateMask();
                }
                
                else if (Input.GetKeyDown(KeyCode.N)) {
                    Instantiate(gameObject);
                    Destroy(gameObject);
                }
                
                else if (Input.GetMouseButton(0)) dragMode = true;
            }
            
            else { // dragmode
                if (!Input.GetMouseButton(0)) {
                    dragMode = false;
                }
                else {
                    
                    mousePosition = Input.mousePosition;
                    mousePosition = Camera.main.ScreenToWorldPoint(mousePosition);
                    transform.position = mousePosition;
                    //fingerPosition = Input.GetTouch(0).position;
                    //fingerPosition = Camera.main.ScreenToWorldPoint(fingerPosition);
                    //transform.position = mousePosition;
                    
                    int currentJoint = NearestJoint();
                    
                    if (currentJoint > -1) { //joint selected in range
                        joints[currentJoint] = transform.position;
                        Vector3 t = transform.position;
                        t.z = 6.0f;
                        gameObject.GetComponent<LineRenderer>().SetPosition(currentJoint, t);
                        if (currentJoint==0) {
                            Vector3 o = transform.position;
                            o.z = 6.0f;
                            gameObject.GetComponent<LineRenderer>().SetPosition(numLines, o);
                        }
                    }
                    else dragMode = false;
                }
            }
                
            for (int i = 0; i < joints.Count-1; i++) {
                Debug.DrawLine (joints[i], joints[i+1], Color.white);
            }
            Debug.DrawLine (joints[joints.Count-1], joints[0], Color.white);
            
        }
    }
    
    //http://wiki.unity3d.com/index.php?title=TextureDrawLine
    void DrawLine(Texture2D tex, int x0, int y0, int x1, int y1, Color col)
    {
        int dy = (int)(y1-y0);
        int dx = (int)(x1-x0);
        int stepx, stepy;
     
        if (dy < 0) {dy = -dy; stepy = -1;}
        else {stepy = 1;}
        if (dx < 0) {dx = -dx; stepx = -1;}
        else {stepx = 1;}
        dy <<= 1;
        dx <<= 1;
     
        float fraction = 0;
     
        tex.SetPixel(x0, y0, col);
        tex.SetPixel(x0+1, y0, col);
        tex.SetPixel(x0+2, y0, col);
        if (dx > dy) {
            fraction = dy - (dx >> 1);
            while (Mathf.Abs(x0 - x1) > 1) {
                if (fraction >= 0) {
                    y0 += stepy;
                    fraction -= dx;
                }
                x0 += stepx;
                fraction += dy;
                tex.SetPixel(x0, y0, col);
                tex.SetPixel(x0+1, y0, col);
                tex.SetPixel(x0+2, y0, col);
            }
        }
        else {
            fraction = dx - (dy >> 1);
            while (Mathf.Abs(y0 - y1) > 1) {
                if (fraction >= 0) {
                    x0 += stepx;
                    fraction -= dy;
                }
                y0 += stepy;
                fraction += dx;
                tex.SetPixel(x0, y0, col);
                tex.SetPixel(x0+1, y0, col);
                tex.SetPixel(x0+2, y0, col);
            }
        }
    }
    
    void LassoClosed () {
        Debug.Log("Is this crop okay? [y/n]");
        GetComponent<Renderer>().material = whitemat;
    }
    
    int NearestJoint() {
        int i = joints.Count;
        int selected = i-1;
        float distance = 900;
        while (i > 0) {
            i--;
            float d = Vector3.Distance(transform.position, joints[i]);
            if (d < distance) {
                distance = d;
                selected = i;
            }
        }
        
        if (distance < jointDistance) return selected;
        return -1;
    }
    
    void GenerateMask () {
        int i = joints.Count;
        int leftmost =   i-1;
        int upmost =     i-1;
        int rightmost =  i-1;
        int downmost =   i-1;
    
        float x = 10;
        while (i > 0) {
            i--;
            float y = joints[i].x;
            if (y < x) {
                x = y;
                leftmost = i;
            }
        }
        
        x = -10;
        i = joints.Count;
        while (i > 0) {
            i--;
            float y = joints[i].y;
            if (y > x) {
                x = y;
                upmost = i;
            }
        }
        
        x = -10;
        i = joints.Count;
        while (i > 0) {
            i--;
            float y = joints[i].x;
            if (y > x) {
                x = y;
                rightmost = i;
            }
        }
        
        x = 10;
        i = joints.Count;
        while (i > 0) {
            i--;
            float y = joints[i].y;
            if (y < x) {
                x = y;
                downmost = i;
            }
        }
        
        Debug.Log(rightmost + " " + leftmost + " " + upmost + " " + downmost);
        
        //convert to per pixel vvv
        float leftm =   Camera.main.WorldToScreenPoint(joints[leftmost]).x -5;
        float upm =     Camera.main.WorldToScreenPoint(joints[downmost]).y -5;
        float rightm =  Camera.main.WorldToScreenPoint(joints[rightmost]).x +5;
        float downm =   Camera.main.WorldToScreenPoint(joints[upmost]).y +5;
        
        Debug.Log(rightm + " " + leftm + " " + upm + " " + downm);
        
        int widthm = (int)rightm - (int)leftm;
        int heightm =  (int)downm - (int)upm;
        if (widthm % 2 == 1) widthm += 1;
        if (heightm % 2 == 1) heightm += 1;
        
        Debug.Log(widthm + " " + heightm);
        
        texture = new Texture2D((int)Mathf.Abs(rightm - leftm), (int)downm - (int)upm);
        texturePos.x =      leftm;
        texturePos.y =      Screen.height-downm;
        texturePos.width =  rightm - leftm;
        texturePos.height = downm - upm;
        
        Debug.Log(leftm);
       
        Debug.Log("Draw_rec" + texturePos);
       
       
        //blit lines
        int x0 = 0;
        int y0 = 0;
        int x1 = 0;
        int y1 = 0;
        for (int k = 0; k < joints.Count-1; k++) {
            x0 = (int) (Camera.main.WorldToScreenPoint(joints[k]).x - texturePos.x);
            y0 = (int) (Camera.main.WorldToScreenPoint(joints[k]).y - downm);
            x1 = (int) (Camera.main.WorldToScreenPoint(joints[k+1]).x - texturePos.x);
            y1 = (int) (Camera.main.WorldToScreenPoint(joints[k+1]).y - downm);
            
            DrawLine(texture, x0, y0, x1, y1, Color.red);
        }
        x0 = (int) (Camera.main.WorldToScreenPoint(joints[joints.Count-1]).x - texturePos.x);
        y0 = (int) (Camera.main.WorldToScreenPoint(joints[joints.Count-1]).y - downm);
        x1 = (int) (Camera.main.WorldToScreenPoint(joints[0]).x - texturePos.x);
        y1 = (int) (Camera.main.WorldToScreenPoint(joints[0]).y - downm);
        
        DrawLine(texture, x0, y0, x1, y1, Color.red);
       
        //fill texture row by row, skipping gaps, bottom up
        
        for (int g = 0; g < texture.height; g++) {
            bool drawing = false;
            int flips = 0;
            for (int h = 1; h < texture.width-2; h++) {
                if (texture.GetPixel(h, g) != Color.red && texture.GetPixel(h+1, g) == Color.red) {
                    drawing = !drawing;
                    flips++;
                }
                if (drawing) texture.SetPixel(h, g, maskColor);
            }
            if (flips % 2 == 1){ //fix bleed
                Debug.Log("flips");
                for (int h = 1; h < texture.width-2; h++) {
                    texture.SetPixel(h, g, Color.blue);
                }
            }
        }
       
        texture.Apply();
        
        Debug.Log("Rec drawn");
        
        //https://docs.unity3d.com/ScriptReference/Texture2D.SetPixel.html
    }
    
    void OnGUI () {
        GUI.DrawTexture(texturePos, texture, ScaleMode.StretchToFill, true);
        
        lines = new Vector3[joints.Count];
        for (int i = 0; i < joints.Count; i++)
        {
            lines[i] = joints[i];
        }
    }
    
    
}