[33mcommit b2a98cb39d02f61557699416e5dba19514277a1d[m[33m ([m[1;31morigin/Pdf-Reading[m[33m)[m
Author: Juan <juan.rivaldo@connect.qut.edu.au>
Date:   Fri Sep 13 13:19:30 2024 +1000

    Edited the pdf-reader-view.fxml file and added most of the code for a working zoom function.
    - Zoom buttons also set up but just need to interact with the zoom code for it to work

[33mcommit 5d9d60d64fd9ff74cd534346ba5313d2f52fe7e1[m
Author: Juan <juan.rivaldo@connect.qut.edu.au>
Date:   Thu Sep 12 23:04:51 2024 +1000

    Created new test to check if non PDF files throw an exception. Test has passed and now the pdfReader class can gracefully fail if the file provided is not a pdf.

[33mcommit 6b79bbb8eb2bf1273250765744fd26955580a811[m
Author: Juan <juan.rivaldo@connect.qut.edu.au>
Date:   Thu Sep 12 02:06:01 2024 +1000

    Added a test pdf file to a directory in src, also improved the first unit test.
    Reworked the PdfReader class to handle exceptions, provide a popup on the interface for the user and fail gracefully.

[33mcommit 8a38c9d54d9ddb6391a326f6d3257208e28f9fac[m
Author: Juan <juan.rivaldo@connect.qut.edu.au>
Date:   Wed Sep 11 23:40:00 2024 +1000

    Added a file path check to pdfReader, needs to be polished

[33mcommit ecc0e5417c2b65f30ee79928f09bbc50827f832b[m
Author: jr991 <juan.rivaldo@connect.qut.edu.au>
Date:   Wed Sep 11 18:09:32 2024 +1000

    Set up structure for the unit testing in both classes

[33mcommit 1df09d03928bc5fda9ab64b4786f5444c7db6188[m
Author: jr991 <juan.rivaldo@connect.qut.edu.au>
Date:   Wed Sep 11 15:33:00 2024 +1000

    Added a junit dependency and a PdfReaderTest class to begin unit testing

[33mcommit c8df06b00c8d3817576558b7f426c209c1ff3fad[m
Author: jr991 <juan.rivaldo@connect.qut.edu.au>
Date:   Tue Sep 10 19:29:40 2024 +1000

    Added more functionality to the Textbook Reader stage such as displaying textbook names while setting the groundwork for the zoom function.
    
    Fixed scaling issues on a few scenes in the stage.
    
    Changed up the pdfReader class to provide two string variables which contain the textbook's title and text as strings.

[33mcommit a18cd7cced9963705742f4ab0d1f3d9bbc4ab08e[m
Author: Juan <juan.rivaldo@connect.qut.edu.au>
Date:   Thu Sep 5 19:59:29 2024 +1000

    Created a stage for the pdf reading window.
     - Adjusted the readPdf method to function with the application and javafx
     - Added half of the functionality for the stage:
       - Adaptable header and text content
     - Created a CSS stylesheet for the stage to be revisited in the future

[33mcommit 343d3863f3322ceae723553c0b82be1000293352[m
Merge: 3ba07f5 53f09e4
Author: Juan <juan.rivaldo@connect.qut.edu.au>
Date:   Thu Sep 5 19:55:25 2024 +1000

    Created a stage for the pdf reading window.
     - Adjusted the readPdf method to function with the application and javafx
     - Added half of the functionality for the stage:
       - Adaptable header and text content
     - Created a CSS stylesheet for the stage to be revisited in the future

[33mcommit 3ba07f55cc60fd7416ab22e6883846e418015584[m
Author: Juan <juan.rivaldo@connect.qut.edu.au>
Date:   Thu Sep 5 19:33:14 2024 +1000

    Created a stage for the pdf reading window.
     - Adjusted the readPdf method to function with the application
     - Added half of the functionality for the stage:
       - Adaptable header and text content
     - Created a CSS stylesheet for the stage to be revisited in the future

[33mcommit 69c3abbee300b016b163d2da01b04770364c6781[m
Author: Juan <juan.rivaldo@connect.qut.edu.au>
Date:   Thu Sep 5 19:11:14 2024 +1000

    simple pdfReader

[33mcommit 53f09e47c2cab25b3b7a95fb7061a4c401fb3306[m
Author: jr991 <juan.rivaldo@connect.qut.edu.au>
Date:   Thu Aug 22 21:52:06 2024 +1000

    Test pdf reader class to be integrated in the future

[33mcommit 682f3b46dd3d55638e467ac300a7cfc1ff7d3683[m
Author: jr991 <juan.rivaldo@connect.qut.edu.au>
Date:   Thu Aug 22 21:51:16 2024 +1000

    Test pdf reader class to be integrated in the future
