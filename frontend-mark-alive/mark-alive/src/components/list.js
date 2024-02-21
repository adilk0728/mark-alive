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
          {data.map((element) => (
            <tr key={element.id}>
              {Object.values(element).map((v) => (
                <td>{v}</td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default List;
