package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
<<<<<<< HEAD
}
=======
}
>>>>>>> 7da2f32c57c3d4067ffb0da3f26a76c77c58ecf7
