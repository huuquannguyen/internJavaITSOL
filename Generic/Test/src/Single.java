public class Single<A extends Number> {
    A value;

    Single (A a){
        this.value = a;
    }

    public boolean compareAbs(Single<A> b){
        return Math.abs(value.doubleValue()) == Math.abs(b.value.doubleValue());
    }
}
