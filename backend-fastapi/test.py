from sqlmodel import Field, Session, SQLModel, create_engine, select

from models.models import Article

sqlite_url = f"mysql+pymysql://root:5211314@localhost:3306/realworld?charset=utf8mb4"

engine = create_engine(sqlite_url, echo=True)


def create_db_and_tables():
    SQLModel.metadata.create_all(engine)



def select_heroes():
    with Session(engine) as session:
        statement = select(Article)
        results = session.exec(statement)
        for hero in results:
            print(hero)


def main():
    # create_db_and_tables()
    # create_heroes()
    select_heroes()


if __name__ == "__main__":
    main()