import java.util.ArrayList;

public class Products{
    private ArrayList<Duck> products;

    public static Builder newEntity() { return new Products().new Builder(); }

    // Amount of products. Similar to List.size()
    public int size(){
        return products.size();
    }

    // the size of a specific product
    public int getProductSize(int i){
        return products.get(i).getSize();
    }

    private class Duck {
        private int size; // the index of the SELECT field

        private int getSize() {
            return size;
        }

        private Duck(int size){this.size = size;}
    }

    public class Builder {
        private Builder() {}
        public Builder withAmount(int size) {
            Products.this.products = new ArrayList<>();
            for(int i = 0; i < size; i++){
                // random size or static size... or it becomes too difficult for someone like me
                Products.this.products.add(new Duck((int)(Math.random()*3+1)));
            }
            return this;
        }
        public Products build() {return Products.this; }
    }
}


