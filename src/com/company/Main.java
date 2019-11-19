package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import com.company.model.Book;
import com.company.model.Customer;
import com.company.model.Employee;
import com.company.model.Order;
import com.company.model.BookGenre;
import com.company.model.Profit;
import com.company.model.BookAdditional;




public class Main {
	
	static ArrayList<Book> books = new ArrayList<>();
	static ArrayList<Customer> customers = new ArrayList<>();
	static ArrayList<Employee> employees = new ArrayList<>();
	static ArrayList<Order> orders = new ArrayList<>();

	public static void main(String[] args) {
	      
		initData();
		
		String booksInfo = 
				String.format("Общее кол-во проданных книг: %d на сумму %f",getCountOfSoldBooks(),getAllPriceOfSoldBooks());
		  
		   System.out.println(booksInfo);

	
	
	
	   for(Employee employee : employees) {
		   
		   System.out.println(employee.getName()  +  " продал(а): " + getProfitByEmployee(employee.getId()) .toString());
	   }
	  
	   ArrayList<BookAdditional> soldBooksCount = getCountOfSoldBooksByGenre();  
		   HashMap<BookGenre,Double> soldBookPrices = getPriceOfSoldBooksByGenre();
		   
		   String soldBookStr = "По Жанру: %s продано %d книг общей стоимости %f";
		   for(BookAdditional bookAdditional : soldBooksCount) {
			   
			   double price = soldBookPrices.get(bookAdditional.getGenre());
			   
			   System.out.println(
					   String.format(
			                     soldBookStr,
			                       bookAdditional.getGenre().name(),bookAdditional.getCount(),price));
			   
		   }
		
		   int age = 30;
		   String analizeGenreStr = "Покупатели младше %d лет выберают жанр %s";
				   System.out.println(String.format(analizeGenreStr,30,getMostPopularGenreLessThenAge(30)));
		   
				   
				
				   String analizeGenreStr2 = "Покупатели старше %d лет выберают жанр %s";
						   System.out.println(String.format(analizeGenreStr2,30,getMostPopularGenreMoreThenAge(30)));
				   
	   }
	   
	
	public static BookGenre getMostPopularGenreLessThenAge( int age) {
		ArrayList<Long> customersIds = new ArrayList<>();
		
		
		for(Customer customer : customers) {
			if(customer.getAge() < age) {
				customersIds.add(customer.getId());
				
	}
		}
			return getMostPopularBookGenre(customersIds);
		  
		}  
	
	
	public static BookGenre getMostPopularGenreMoreThenAge( int age) {
		ArrayList<Long> customersIds = new ArrayList<>();
		
		
		for(Customer customer : customers) {
			if(customer.getAge() > age) {
				customersIds.add(customer.getId());
				
	}
		}
			return getMostPopularBookGenre(customersIds);
		  
		}  
	

	private static BookGenre getMostPopularBookGenre(ArrayList<Long> customersIds) {
		int countArt = 0, countPr = 0, countPs = 0;
		
		
		for(Order order : orders) {
			if(customersIds.contains(order.getCustomerId())) {	
			countArt += getCountOfSoldBooksByGenre(order,BookGenre.Art);
			countPr += getCountOfSoldBooksByGenre(order,BookGenre.Programming);
			countPs += getCountOfSoldBooksByGenre(order,BookGenre.Psihology);
		
		}
}

ArrayList<BookAdditional> result = new ArrayList<>();
result.add( new BookAdditional(BookGenre.Art,countArt));
result.add( new BookAdditional(BookGenre.Programming,countPr));
result.add( new BookAdditional(BookGenre.Psihology,countPs));

  result.sort( new Comparator<BookAdditional>() {  
		@Override
		public int compare(BookAdditional left, BookAdditional right) {
		    return right.getCount() - left.getCount();
  }	  
});

  return result.get(0).getGenre();
	}

	
	
	public static ArrayList<BookAdditional> getCountOfSoldBooksByGenre(){
		ArrayList<BookAdditional> result = new ArrayList<>();
		int countArt = 0,countPr = 0,countPs = 0;
		
		
		for(Order order : orders) {
			  countArt += getCountOfSoldBooksByGenre(order,BookGenre.Art);
			  countPr += getCountOfSoldBooksByGenre(order,BookGenre.Programming);
			  countPs += getCountOfSoldBooksByGenre(order,BookGenre.Psihology);
		}
		
		result.add( new BookAdditional(BookGenre.Art,countArt));
		result.add( new BookAdditional(BookGenre.Programming,countPr));
		result.add( new BookAdditional(BookGenre.Psihology,countPs));
			  
		
	          return  result;
	
}


	
	public static int getCountOfSoldBooksByGenre(Order order , BookGenre genre) {
          int count = 0;
		
		for(long bookId : order.getBooks()) {
			Book book = getBookById(bookId);
			if(book != null && book.getGenre() == genre)
			count ++;
		}
		  return count ;
	  }
	
	
	
	public static HashMap<BookGenre , Double> getPriceOfSoldBooksByGenre(){
		HashMap<BookGenre,Double> result = new HashMap<>();
		double priceArt = 0, pricePr = 0, pricePs = 0;
		
		  for(Order order : orders) {
			  priceArt += getPriceOfSoldBooksByGenre(order,BookGenre.Art);
			  pricePr += getPriceOfSoldBooksByGenre(order,BookGenre.Programming);
			  pricePs += getPriceOfSoldBooksByGenre(order,BookGenre.Psihology);
		  }
		
		result.put(BookGenre.Art,priceArt);
		result.put(BookGenre.Programming,pricePr);
		result.put(BookGenre.Psihology,pricePs);
		
		  return result;
	}
	

	

	public static double getPriceOfSoldBooksByGenre(Order order , BookGenre genre) {
      double price = 0;
		
		for(long bookId : order.getBooks()) {
			Book book = getBookById(bookId);
			if(book != null && book.getGenre() == genre)
			price += book.getPrice();
		}
		  return price;
		
	}
	
	
	
	
	
	public static Profit getProfitByEmployee(long employeeId) {
              int count = 0; double price = 0;
            
           for(Order order : orders) {
        	   if(order.getEmployeeId() == employeeId) {
        		 
        		   price += getPriceOfSoldBookOrder(order);
        		   count += order.getBooks().length;
        	   }
           }
        	   
        	  return new Profit(count,price);
           }
              
              
              
              

	public static double getAllPriceOfSoldBooks() {
		double price = 0;
		
		for (Order order : orders) {
			price += getPriceOfSoldBookOrder(order);
			
		}
	
		return price;
	}
	

	public static double getPriceOfSoldBookOrder(Order order) {
		double price = 0;
		
		for(long bookId : order.getBooks()) {
			Book book = getBookById(bookId);
			if(book != null)
			price += book.getPrice();
		}
		  return price;
	}
	
	
	public static int getCountOfSoldBooks() {
		int count = 0;
		for(Order order : orders) {
			count += order.getBooks().length;
			
		}
			
			return count;
		
	}
	
	public static  Book getBookById(long id) {
		Book current = null;
		
		for(Book book : books) {
			if(book.getId() == id) {
				current = book;
			break;
		}
	}
	  return current;
	  
}
	
	public static void initData(){
		
		
		employees.add( new Employee(1,"Иванов Иван",32));
		employees.add( new Employee(2,"Петров Петр ",30));
		employees.add( new Employee(3,"Васильева Алиса",26));
		
		
		
		
		customers.add( new Customer(1,"Сидоров Алексей",25));
		customers.add( new Customer(2,"Романов Дмитрий",32));
		customers.add( new Customer(3,"Симонов Кирилл",19));
		customers.add( new Customer(4,"Кириенко Данил",45));
		customers.add( new Customer(5,"Вортынцева Элина",23));
		
		
		
		
		books.add( new Book(1,"Война и мир","Толстой Лев",1500,BookGenre.Art));
		books.add( new Book(2,"Преступление и наказание","Достоевский Федор",600,BookGenre.Art));
		books.add( new Book(3,"Мертвые Души","Гоголь Николай",750,BookGenre.Art));
		books.add( new Book(4,"Руслан и Людмила","Пушкин Александр",500,BookGenre.Art));
		
		
		
		books.add( new Book(5,"Ввидение в психоанализ","Фрейд Зигмунд",1050,BookGenre.Psihology));
		books.add( new Book(6,"Психология влияния.Убеждайся.Воздействуй.Защищайся","Чалдини Роберт",550,BookGenre.Psihology));
		books.add( new Book(7,"Как перестать беспокоиться и начать жить","Карнеги Дейл",1000,BookGenre.Psihology));
		
		
		
		books.add( new Book(8,"Gang of Four","Гамма Эрих",900,BookGenre.Programming));
		books.add( new Book(9,"Совершенный код","Маккоммел Стив",1200,BookGenre.Programming));
		books.add( new Book(10,"Рефакторинг. Улучшение существующего кода","Фаулер Мартин",850,BookGenre.Programming));
		books.add( new Book(11,"Алгоритмы. Построение и анализ","Гамма Эрих",500,BookGenre.Programming));
		
		
		
		
		orders.add( new Order(1,1,1, new long[] {8,9,10,11}));
		orders.add( new Order(2,1,2, new long[] {1}));
		
		
		orders.add( new Order(3,2,3, new long[] {5,6,7}));
		orders.add( new Order(4,2,4, new long[] {1,2,3,4}));
		
		
		orders.add( new Order(5,3,5, new long[] {2,5,9}));
		
		
		
		
		
	}

}
