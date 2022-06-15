make

clear


echo "new A B 0 1 1 2 3 4 5 2 3 4 5 6 3 2 -1 4 3 exit" | java -jar ConnectFour.jar | grep "has won"
echo "expected: A winner"
echo ""


echo "new A B 5 0 1 1 2 3 4 5 2 3 4 5 6 3 2 -1 4 3 exit" | java -jar ConnectFour.jar | grep "has won"
echo "expected: B winner"
echo ""


echo "new A B 5 0 1 1 test 2 3 4 5 2 not a command 3 4 5 6 3 2 -1 4 save out exit" | java -jar ConnectFour.jar | ls | grep "out.dat"
echo "expected: out.dat"
echo ""


echo "load out 3 exit" | java -jar ConnectFour.jar | grep "has won"
echo "expected: B winner"
echo ""
rm -rf out.dat

echo "test 123 a b exit" | java -jar ConnectFour.jar | grep "not found"
echo "expected: not crashing"
echo ""

echo "new A B test ciao not working exit exit" | java -jar ConnectFour.jar | grep "playing"
echo "expected: not crashing"
echo ""

echo "new A B 12 123 -1 -2000 2147483647 -2147483648 exit exit" | java -jar ConnectFour.jar | grep "column"
echo "expected: not crashing"
echo ""

echo "load test exit" | java -jar ConnectFour.jar | grep "not found"
echo "expected: not crashing"
echo ""


echo "help exit" | java -jar ConnectFour.jar | grep "help:"
echo "expected: print help info"
echo ""

echo "new A B help exit exit" | java -jar ConnectFour.jar | grep "help:"
echo "expected: print help info"
echo ""
