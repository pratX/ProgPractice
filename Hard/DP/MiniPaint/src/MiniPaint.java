import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by pratikku on 30/8/15.
 */
public class MiniPaint {
    public static int leastBad(String[] picture, int maxStrokes){
        class Node{
            int strokes;
            int misPaints;
            Node next;
            public Node(int s, int mp){
                strokes = s;
                misPaints = mp;
                next=null;
            }
        }

        class NodeKV implements Comparable<NodeKV>{
            int key;
            int val;
            public NodeKV(int k, int v){
                this.key = k;
                this.val = v;
            }
            public int compareTo(NodeKV n){
                if(this.key == n.key)
                    return 0;
                if(this.key>n.key)
                    return 1;
                return -1;
            }
            public boolean equals(NodeKV n){
                if(this.key == n.key)
                    return true;
                return false;
            }
        }

        class NodeKVV implements Comparable<NodeKVV>{
            int key;
            int misPaints;
            int strokes;

            public NodeKVV(int k, int mp, int s){
                key = k;
                misPaints = mp;
                strokes = s;
            }

            public int compareTo(NodeKVV n){
                if(n.key == this.key)
                    return 0;
                if(this.misPaints > n.misPaints)
                    return 1;
                if(this.misPaints < n.misPaints)
                    return -1;
                if(this.misPaints == n.misPaints){
                    if(this.strokes > n.strokes )
                        return -1;
                    if(this.strokes <= n.strokes)
                        return 1;
                }
                return 1;
            }
        }

        class CompareKV implements Comparator<NodeKV>{
            public int compare(NodeKV n1, NodeKV n2){
                if(n1.key == n2.key)
                    return 0;
                if(n1.val > n2.val){
                    return 1;
                }
                return -1;
            }
        }

        Node[] rows = new Node[picture.length];
        //Populate rows
        for(int i=0; i<rows.length; ++i){
            TreeSet<NodeKV> keys = new TreeSet<>();
            TreeSet<NodeKV> values = new TreeSet<>(new CompareKV());
            int pos=0;
            char c = picture[i].charAt(pos++);
            int count=1;
            int key = 0;
            while(pos < picture[i].length()){
                if(picture[i].charAt(pos) == c) {
                    ++count;
                    ++pos;
                }
                else{
                    NodeKV n = new NodeKV(key, count);
                    keys.add(n);
                    values.add(n);
                    count=1;
                    c = picture[i].charAt(pos);
                    ++key;
                    ++pos;
                }
            }
            NodeKV n = new NodeKV(key, count);
            keys.add(n);
            values.add(n);
            rows[i] = new Node(key+1, 0);
            Node curr = rows[i];

            while(!keys.isEmpty()){
                NodeKV p = values.first();
                NodeKV p_prev = keys.lower(p);
                NodeKV p_next = keys.higher(p);
                if(((p_prev == null) && (p_next !=null))||((p_prev!=null)&&(p_next==null))){
                    NodeKV q = values.higher(p);
                    while((q != null)&&(q.val == p.val)){
                        if((keys.lower(q)!=null)&&(keys.higher(q)!=null)){
                            p = q;
                            p_prev = keys.lower(q);
                            p_next = keys.higher(q);
                            break;
                        }
                        else{
                            q = values.higher(q);
                        }
                    }
                }
                if((p_prev != null) && (p_next!=null)){
                    curr.next = new Node(curr.strokes-2, curr.misPaints+p.val);
                    curr = curr.next;
                    values.remove(p); keys.remove(p);
                    values.remove(p_next); keys.remove(p_next);
                    values.remove(p_prev); keys.remove(p_prev);
                    NodeKV ins = new NodeKV(p_prev.key, p_prev.val+p.val+p_next.val);
                    values.add(ins);
                    keys.add(ins);
                }
                else if((p_prev!=null) && (p_next == null)){
                    curr.next = new Node(curr.strokes-1, curr.misPaints+p.val);
                    curr = curr.next;
                    values.remove(p); keys.remove(p);
                    values.remove(p_prev); keys.remove(p_prev);
                    NodeKV ins = new NodeKV(p_prev.key, p_prev.val+p.val);
                    values.add(ins);
                    keys.add(ins);
                }
                else if((p_prev==null) && (p_next != null)){
                    curr.next = new Node(curr.strokes-1, curr.misPaints+p.val);
                    curr = curr.next;
                    values.remove(p); keys.remove(p);
                    values.remove(p_next); keys.remove(p_next);
                    NodeKV ins = new NodeKV(p_next.key, p_next.val+p.val);
                    values.add(ins);
                    keys.add(ins);
                }
                else{
                    curr.next = new Node(0,p.val);
                    values.remove(p); keys.remove(p);
                }
            }
        }

        TreeSet<NodeKVV> min_mp = new TreeSet<>();
        int strokes=0;
        int misPaints=0;

        for(int i=0; i<rows.length; ++i){
            strokes += rows[i].strokes;
            if(rows[i].next!=null){
                min_mp.add(new NodeKVV(i, rows[i].next.misPaints - rows[i].misPaints, rows[i].strokes - rows[i].next.strokes));
            }
            rows[i] = rows[i].next;
        }

        while(strokes > maxStrokes){
            NodeKVV n = min_mp.first();
            System.out.print("row:"+n.key+" strokes_subtracted:"+n.strokes+" misPaints_added:"+n.misPaints);
            System.out.format("%n");
            strokes -= n.strokes;
            misPaints += n.misPaints;
            min_mp.remove(n);
            if((rows[n.key] != null) && (rows[n.key].next != null)){
                min_mp.add(new NodeKVV(n.key, rows[n.key].next.misPaints - rows[n.key].misPaints, rows[n.key].strokes - rows[n.key].next.strokes));
                rows[n.key] = rows[n.key].next;
            }
        }

        return misPaints;
    }

    public static void main(String[] args){
        System.out.println(MiniPaint.leastBad(new String[] 	{"BBBWWWWBWWWWBBWWWBWWWBWBWBBBWWWWBWWBWBWBWWBBBWBBWW", "BWBWBWBWWWBWWBWBWWBWWBBWBBWWWWWBWWBBWBWWBBBWBBBWBB", "BWWBBWBBBBBWWWBWWBWWBWBWBBBBBWWBBBWBWWBWWBBBBBBWWW", "BWWWBWWBBBWBBWWWBWBWBBBWWBBBWBBWBBWBWBBBWBBBWWBBBB", "WWBWBWWBBWWBBBWWWBWWWBWBWBBBBWBBWWBBBWBWWBBWBWBWWB", "BWWWWBWWBWWWWWBBBWWBWWBWWBBWBBWWBWWWWWWBBWWWWWWBWW", "WBWWBWBWWBWBBBWWBWBBWWBWWWWWBBBWWWWWWWWBWWBWBBWBWW", "BWBBWBWWWWWBWBBBBWBBBBBBWBWBBBWBWBWWBBWBBWBBWWBBWB", "BBBBBWWBWWWWWBWWWBBBBWBWWBBWBWBWBBWBBBWBBWWBBWWWWB", "BWBWBBWWBWBBWWBWWWBBBBBBBWBBWWWBWBWWBBBWWBBWWBWBBB", "BBBWWBWBBWWBBWBBBWBBWBBWWBBWWWWWWWWBBBWWWBBBWWBWWW", "WBBBBBWWWBWWWWBWBBWBWBWWWBWWBBWBBWBWWBBBBBWWBWWBBW", "BWWBBWBWBWWWWWBBWBWWBBWWBWWBBBWWBBBWBBWWBWBBBBBWWB", "WWWWBWBWWBBBWWBBWBWBWWBBBBBBWBBBBBBBBBWBWBBBWBWWWB", "WWBBBWBWWBWWBWBBWWWBWWWBWWWBBBBBBBWWBWBBBWWWWBWBWB", "WWWBBBWBBBWBWWWBBWBWBWBWWWBBBBBWBWBWWWWWBBBWWBWBWW", "BWBBBWBWWBWWWBWBWWWBBWWBBWBWWBBWWBWBBBWBWBWWBWWBBB", "WWWBBWBWWBBBBWBBBBWBWBWBBWWWWWBBBWWWBBWWBBBBBBBWWW", "WWBWBWWWBBBBWWBBWBWWWWWBBWBWBBWBWBWBWBBWBWWBWBWBWW", "BBWBWWBBWBBWBWWWWWBBWBWBBWBWWWWWBWBBWBBBBBWWWBBWWW", "BBBWWWBWWBBWBBWBBWWWBWWBBBWWWWWBBBBBWWWBWWBWBBWBBW", "WBWWBBWWBBWBWWBBWBWBWBBBBBBWBWBWWWWWBBWBBBWBBBWWWB", "WBBBWBBWBWWWBBWBBBBWBWWWBWWWWBBWWWBWBWBWBBWWBWBBBB", "BWWWWBWBBBBWBWBBWBBBWBWBWBWBBWWWBWWBWBWBBBBWWBBWBW", "BBWBWBWBBBWBBWWBWWWWBBWWBWBWWWBBWBBBBWBWBWWBWBBWBW", "WWWBWWWWBBBBBBBWBWWBWBWBBWBWWBWBWWWBBBWBBBWWBWWBBB", "WWWWWWBBWWWBWBWWBBBWWWWBWBWBBBWWBWBBBBBBWWWBWBWBWW", "WBBBBBBWWBWBBWWWWWBBWWWWBBBWBWBBBBWBWWBBBWBWBBWWWW", "BBWWBWWBWWBWBBWWBBBWWBWBWBWBBWBWBWBBBWBBBBWWBBBBBB", "BBWBWBBWBBWWBBBWBWBBBBWWBBBWWBBBWWBWBBWBBBWBBBBBBB", "WWWWWBWBBWWWBBWWWWWWBBWBWBBWBBBWWBWBWWWWWBWWWBBWWW", "WWWBBWWBWBBBBWWWBWBWWWBBBBBBBWWBWBWWWBWWWWWWBBWBWB", "BBWBWWWBWWWWWBBBBBBWBWBBWWWWBBWBBWBBBBWBWBBBWWBBWB", "BWBWWWWBBWBBBBWWWBBWWWWBBBWWWBBBBWWWWWWWBBWWWBWWBW", "WWWBWBBBWBWWBWWWWWWBWWWBWBWWBBWBWWBWWWBWBWBWBBBBBW", "WBWWBBWWWBWWWWWWWBBWWBBBBBWWWWWWWBWWWBBWBWWBWBBWBW", "BBWBWWWWBBWWBWWWBBWBWBBWBBBWBWWBWBWBBWWWWWBBWBWWWB", "BBBWBBWWBWWBWWBBWBWBBBWWWWBWBWBBBBBBBWWBBWWBBBWBWB", "WBBWWWBBBWBWWWWWBWWWWWWBBBWWWBBBWBWBWWBBBWWBBBWBWB", "BBBBWBWWWBWBWWWWBWWWBBBWWBWBWWWBBWBBWBWBBWBWWWBWBW", "BBBWWBWWWWBWWWBWBWBBBWWWBWBWBBBBWBBBBBWWBBBBWWBBBW", "BWBWBWBBBBBWBBBWWWWBBWWBWBBBWBBWWBWBWWWBWBBBBWBWWB", "WWBWBWWBBBWWBBWBBBWBBBWBBBBWWBWWWBWWWWWBWWBBWBWWBW", "WWWBBBWWWBBWBWBBWBWWWWBBWBWWWWBWBWBWBWWBBWBBWBWBWW", "WWWBWWWBWWWBBBWWBBBBBBBBWWBBBBBWWBBBWBBBBBBWWBWWWB", "BWBWWBBBWBBWBWWBBWBWWWWBBWBWBBBWWWWBBWBBBWWBBBWBBB", "BBWWWBWBWWBBWWBWBWBWBBWWWWBWBBBBBWBWBBWBWWWBBWBBBW", "BWWWBBWBWBBWBWBWBWWWBWBWBBBBWWBBWBBBBWBBBBWWBBWWBB", "WBBWBWWBBWWBWWBWWWWWWWWBBBBBWWWBBBBWBBWBWWBWBBWWWW", "BBBBBWWBWBBWBWWWWBBWWBBBBWBWWBBBBBBBBBWWWBBWWBBWBW"}, 139));
    }
}
