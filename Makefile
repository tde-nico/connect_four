NAME		= ConnectFour

SRC_DIR		= srcs
OBJ_DIR		= class

SRCS		= $(wildcard $(SRC_DIR)/*.java)
OBJS		= $(SRCS:$(SRC_DIR)/%.java=$(OBJ_DIR)/%.class)

JC			= javac
CFLAGS		= -Werror -sourcepath $(SRC_DIR)/ -d $(OBJ_DIR)/
LINKER		= jar
LFLAGS		= -cf
RM			= rm -rf

$(OBJ_DIR)/%.class : $(SRC_DIR)/%.java
	@mkdir -p $(OBJ_DIR)
	$(JC) $(CFLAGS) $<

$(NAME).jar: $(OBJS)
	$(LINKER) $(LFLAGS) $@ $(OBJS)
	@echo "[+] $(NAME) compiled"

all: $(NAME).jar

clean:
	@$(RM) $(OBJ_DIR)
	@echo "[+] $(NAME) cleaned"	

fclean: clean
	@$(RM) $(NAME).jar
	@echo "[+] $(NAME) fcleaned"

re: fclean all

run: all
	@java -cp $(OBJ_DIR) $(NAME)Tester

tar:
	@tar -cf ../$(NAME).tar .
	@echo "[+] Made Tar"

.PHONY: all clean fclean re $(NAME)
