export const List = ({ Headers, data }) => {
  return (
    <div>
      <table>
        <thead>
          <tr>
            {Headers.map((element, index) => (
              <th key={index}>{element}</th>
            ))}
          </tr>
        </thead>
        <tbody>
          {data.map((element, index) => (
            <tr key={element.id}>
              <td>{element.id}</td>
              <td>{element.url}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default List;
