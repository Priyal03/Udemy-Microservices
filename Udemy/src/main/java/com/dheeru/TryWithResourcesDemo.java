package com.dheeru;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TryWithResourcesDemo {
	
	static String file_input="/Users/coder/git/Udemy/Udemy/src/main/java/com/dheeru/cheatsheet.jpg";
	static String file_op="cheat.jpg";
	
	public static void main(String[] args) {
		try {
		//	fileCopyWithARM();
			fileCopyWithoutArmSol();
		} catch (IOException e) {
			e.printStackTrace();
		
		Throwable[] throwables = e.getSuppressed();
		System.out.println(throwables[0].getMessage());
		System.out.println(throwables[1].getMessage());
		}
	}
	
	//automatic resource management
	public static void fileCopyWithARM() throws IOException{
		
		System.out.println("INside filecopy with ARM");
		
		try(Test t1=new Test(); Test2 t2= new Test2(); BufferedInputStream is = new BufferedInputStream(new FileInputStream(file_input));
				BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file_op))) {
			{
				byte[] byteBuf = new byte[4000];
				int numBytesRead;
				while ((numBytesRead = is.read(byteBuf)) != -1) {
					os.write(byteBuf, 0, numBytesRead);
				}		
				
				throw new IOException("Important Exception!!");		
//the other two exceptions are suppressed under this Important Exception as two times this exception gets called for 
//two different AutoCloseable classes exception t1 and t2.
			}
		}
	}
	public static void fileCopyWithoutArm() throws IOException {
		System.out.println("\nInside fileCopyWithoutArm ...");
		
		Test t = null;
		Test2 t2 = null;
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		
		try {
			t = new Test();
			t2 = new Test2();
			in = new BufferedInputStream(new FileInputStream(file_input));
			out = new BufferedOutputStream(new FileOutputStream(file_op));
			
			byte[] byteBuf = new byte[4000];
			int numBytesRead;
			while ((numBytesRead = in.read(byteBuf)) != -1) {
				out.write(byteBuf, 0, numBytesRead);
			}
			
			throw new IOException("Important Exception!!");
			
		} finally {
			
			if (t2 != null) 
				t2.close();
			
			if (t != null) 
				t.close();			
			
			if (in != null)
				in.close();
			
			if (out != null)
				out.close();
		}		
	}
	
	// Handles Exception Masking via Suppression
		public static void fileCopyWithoutArmSol() throws IOException {
			System.out.println("\nInside fileCopyWithoutArm2 ...");
			
			Test t = null;
			Test2 t2 = null;
			BufferedInputStream in = null;
			BufferedOutputStream out = null;
			
			IOException ioException = null;		
					
			try {
				t = new Test();
				t2 = new Test2();
				in = new BufferedInputStream(new FileInputStream(file_input));
				out = new BufferedOutputStream(new FileOutputStream(file_op));
				
				byte[] byteBuf = new byte[4000];
				int numBytesRead;
				while ((numBytesRead = in.read(byteBuf)) != -1) {
					out.write(byteBuf, 0, numBytesRead);
				}
				
				throw new IOException("Important Exception!!");
				
			} catch (IOException e) {
				ioException = e;
			} finally {			
				if (ioException != null) {
					try {
						if (t2 != null) {
							t2.close();
						}
					} catch (IOException e) {
						ioException.addSuppressed(e);					
					}				
				} else {
					try {
						if (t2 != null) {
							t2.close();
						}
					} catch (IOException e) {
						ioException = e;					
					}
				}
				
				if (ioException != null) {
					try {
						if (t != null) {
							t.close();
						}
					} catch (IOException e) {
						ioException.addSuppressed(e);					
					}				
				} else {
					try {
						if (t != null) {
							t.close();
						}
					} catch (IOException e) {
						ioException = e;					
					}
				}
				
				if (ioException != null) {
					try {
						if (in != null) {
							in.close();
						}
					} catch (IOException e) {
						ioException.addSuppressed(e);					
					}				
				} else {
					try {
						if (in != null) {
							in.close();
						}
					} catch (IOException e) {
						ioException = e;					
					}
				}
				
				if (ioException != null) {
					try {
						if (out != null) {
							out.close();
						}
					} catch (IOException e) {
						ioException.addSuppressed(e);					
					}				
					
					throw ioException;
				} else {
					if (out != null) {
						out.close();
					}				
				}
			}		
					
		}
}
class Test implements AutoCloseable{

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		throw new IOException("Trivial Exception from test");
	}
	
}

class Test2 implements AutoCloseable{

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		throw new IOException("Trivial Exception frm test2");
	}
	
}