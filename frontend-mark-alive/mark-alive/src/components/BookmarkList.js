import { useEffect, useState } from "react";
import List from "./List";

const BookmarkList = () => {
  const [urlList, setUrlList] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/bookmarks")
      .then((resp) => resp.json())
      .then((data) => setUrlList(data));
  }, [urlList]);
  return (
    <div>
      <List Headers={["ID", "URLs"]} data={urlList} />
    </div>
  );
};

export default BookmarkList;
