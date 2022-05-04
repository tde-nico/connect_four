NAME		= ConnectFour.jar

JC			= javac
CFLAGS		= -Werror
LINKER		= jar
LFLAGS		= -cf
RM			= rm -rf

SRC_DIR		= srcs
OBJ_DIR		= class

SRCS		= $(wildcard $(SRC_DIR)/*.java)
OBJS		= $(patsubst $(SRC_DIR)/%, $(OBJ_DIR)/%, $(SRCS:.java=.class))

$(OBJ_DIR)/%.class : $(SRC_DIR)/%.java
	@mkdir -p $(OBJ_DIR)
	$(JC) $(CFLAGS) $< -d $(OBJ_DIR)

$(NAME): $(OBJS)
	$(LINKER) $(LFLAGS) $@ $(OBJS)
	@echo "[+] $(NAME) compiled"

all: $(NAME)

clean:
	@$(RM) $(OBJ_DIR)
	@echo "[+] $(NAME) cleaned"	

fclean: clean
	@$(RM) $(NAME)
	@echo "[+] $(NAME) fcleaned"

re: fclean all

run: all
	@java $(NAME)

tar:
	@tar -cf ../$(NAME).tar .
	@echo "[+] Made Tar"

.PHONY: all clean fclean re $(NAME)
