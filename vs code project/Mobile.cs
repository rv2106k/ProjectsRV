using System;
using System.Collections.Generic;
using System.Data.SqlTypes;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


    public class Mobile
    {
        string name,colour,storage;
        int price;

        public Mobile(string name, string colour, string storagee, int price)
        {
            this.name = name;
            this.colour = colour;
            this.storage = storagee;
            this.price = price;
        }

        public string getName() {
            return name; 
        }
        public string getColour()
        {
            return colour;
        }
        public int getPrice()
        {
            return price;
        }
        public string getStorage()
        {
            return storage;
        }

        public override string ToString()
        {
            return name + colour + storage + price ;
        }
            public static void Main(String[] args)
    {
        Mobile mobile = new Mobile("Apple","Orange","1 TB",75000);
        Console.WriteLine(mobile);
    }
        
    }

